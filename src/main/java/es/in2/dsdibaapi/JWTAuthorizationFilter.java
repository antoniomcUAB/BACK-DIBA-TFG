package es.in2.dsdibaapi;

public class JWTAuthorizationFilter {
/*extends BasicAuthenticationFilter {
	
	JWTProperties jwtProperties;
	
	
	public JWTAuthorizationFilter(AuthenticationManager authManager, JWTProperties jwtProperties) {
		super(authManager);
		this.jwtProperties = jwtProperties;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(jwtProperties.getHEADER_AUTHORIZATION_KEY());
		if (header == null || !header.startsWith(jwtProperties.getTOKEN_BEARER_PREFIX())) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(jwtProperties.getHEADER_AUTHORIZATION_KEY());
		if (token != null) {
			// Se procesa el token y se recupera el usuario.
			String user = Jwts.parser()
						.setSigningKey(jwtProperties.getJWT_KEY())
						.parseClaimsJws(token.replace(jwtProperties.getTOKEN_BEARER_PREFIX(), ""))
						.getBody()
						.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}*/
}
