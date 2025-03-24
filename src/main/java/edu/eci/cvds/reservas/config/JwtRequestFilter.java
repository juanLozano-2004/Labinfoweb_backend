package edu.eci.cvds.reservas.config;

        import java.io.IOException;
        import java.util.Collections;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
        import org.springframework.stereotype.Component;
        import org.springframework.web.filter.OncePerRequestFilter;

        import edu.eci.cvds.reservas.service.CustomUserDetailsService;
        import edu.eci.cvds.reservas.util.JwtUtil;
        import jakarta.servlet.FilterChain;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        import lombok.AllArgsConstructor;
        import lombok.RequiredArgsConstructor;

        /**
         * Filter that processes JWT authentication for each request.
         */
        @Component
        @AllArgsConstructor
        @RequiredArgsConstructor
        public class JwtRequestFilter extends OncePerRequestFilter {

            @Autowired
            private JwtUtil jwtUtil;

            @Autowired
            private CustomUserDetailsService customUserDetailsService;

            /**
             * Filters incoming requests to authenticate JWT tokens.
             *
             * @param request  the HTTP request
             * @param response the HTTP response
             * @param chain    the filter chain
             * @throws ServletException if an error occurs during the filtering process
             * @throws IOException      if an I/O error occurs during the filtering process
             */
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                    throws ServletException, IOException {

                try {
                    final String authorizationHeader = request.getHeader("Authorization");
                    String username = null;
                    String jwt = null;
                    String role = null;

                    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                        jwt = authorizationHeader.substring(7);
                        username = jwtUtil.extractUsername(jwt);
                        role = jwtUtil.extractClaim(jwt, claims -> claims.get("Role", String.class));
                    }

                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

                        if (jwtUtil.validateToken(jwt, userDetails)) {
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                    }
                } catch (io.jsonwebtoken.ExpiredJwtException e) {
                    System.err.println("Error: El token ha expirado. " + e.getMessage());
                } catch (io.jsonwebtoken.SignatureException e) {
                    System.err.println("Error: Firma del token inválida. " + e.getMessage());
                } catch (io.jsonwebtoken.MalformedJwtException e) {
                    System.err.println("Error: Token mal formado. " + e.getMessage());
                } catch (io.jsonwebtoken.UnsupportedJwtException e) {
                    System.err.println("Error: Token no soportado. " + e.getMessage());
                } catch (io.jsonwebtoken.JwtException e) {
                    System.err.println("Error: Token inválido. " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Error en la autenticación JWT: " + e.getMessage());
                }

                chain.doFilter(request, response);
            }
        }