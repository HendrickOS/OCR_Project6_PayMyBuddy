package com.example.PayMyBuddy.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import com.example.PayMyBuddy.security.jwt.JwtConfig;
import com.example.PayMyBuddy.security.jwt.JwtUtil;

import io.jsonwebtoken.Claims;

public class JwtAuthenticationFilter extends GenericFilterBean {

	private static final String NAME = "name";

	private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	private JwtConfig jwtConfig;

	public JwtAuthenticationFilter() {
		this.jwtConfig = new JwtConfig();
	}

	public JwtAuthenticationFilter(JwtConfig jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	private String generateToken(String userName, Set<String> roles) throws Exception {
		Map<String, String> infos = new HashMap<>();
		String token = JwtUtil.generateToken(userName, jwtConfig.getSigningKey(), jwtConfig.getTokenValidity(), roles,
				infos);
		return token;
	}

	public User getUserFromAuthHeader(HttpServletRequest request, HttpServletResponse response) {
		if (!JwtUtil.tokenPresent(request)) {
			return null;
		}
		try {
			String token = JwtUtil.getTokenFromAuthHeader(request);
			Claims claims = JwtUtil.getClaims(token, jwtConfig.getSigningKey());

			String name = claims.getSubject();
			Collection<String> roles = claims.get(JwtUtil.ROLES_KEY, Collection.class);

			if (isAdmin(roles)) {
				roles.add(Roles.USER);
			}
			if (name != null) {
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				for (String role : roles) {
					authorities.add(new SimpleGrantedAuthority(role));
				}
				return new User(name, "secret", authorities);
			}
		} catch (Exception e) {
			LOG.error("error reading token. Cause: " + e.getMessage());
		}
		return null;
	}

	private boolean containsRole(Collection<String> roles, String role) {
		for (String aux : roles) {
			if (aux.trim().equalsIgnoreCase(role)) {
				return true;
			}
		}
		return false;
	}

	private boolean isAdmin(Collection<String> roles) {
		return containsRole(roles, Roles.ADMIN);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		LOG.debug("doFilter(");

		User user = getUserFromAuthHeader((HttpServletRequest) request, (HttpServletResponse) response);

		Authentication authentication = null;
		if (user != null) {
			authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
}