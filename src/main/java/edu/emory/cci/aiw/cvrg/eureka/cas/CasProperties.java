package edu.emory.cci.aiw.cvrg.eureka.cas;

/**
 *
 * @author Andrew Post
 */
public class CasProperties extends AbstractProperties {
	
	public boolean isEphiProhibited() {
		return Boolean.parseBoolean(getValue("eureka.webapp.ephiprohibited"));
	}

	public boolean isDemoMode() {
		return Boolean.parseBoolean(getValue("eureka.webapp.demomode"));
	}

	public boolean isTwitterAuthEnabled() {
		return getTwitterKey() != null || getTwitterSecret() != null;
	}

	public String getTwitterKey() {
		return getValue("cas.auth.oauth.twitter.key");
	}

	public String getTwitterSecret() {
		return getValue("cas.auth.oauth.twitter.secret");
	}

	public boolean isGoogleAuthEnabled() {
		return getGoogleKey() != null || getGoogleSecret() != null;
	}

	public String getGoogleKey() {
		return getValue("cas.auth.oauth.google.key");
	}

	public String getGoogleSecret() {
		return getValue("cas.auth.oauth.google.secret");
	}

	public boolean isGitHubAuthEnabled() {
		return getGitHubKey() != null || getGitHubSecret() != null;
	}

	public String getGitHubKey() {
		return getValue("cas.auth.oauth.github.key");
	}

	public String getGitHubSecret() {
		return getValue("cas.auth.oauth.github.secret");
	}
	
	public boolean isGlobusAuthEnabled() {
		return getGlobusKey() != null || getGlobusSecret() != null;
	}

	public String getGlobusKey() {
		return getValue("cas.auth.oauth.globus.key");
	}

	public String getGlobusSecret() {
		return getValue("cas.auth.oauth.globus.secret");
	}

	public boolean isOAuthEnabled() {
		return isTwitterAuthEnabled()
				|| isGoogleAuthEnabled()
				|| isGitHubAuthEnabled()
				|| isGlobusAuthEnabled();
	}

	public boolean isOAuthDirectLogin() {
		if (getValue("cas.auth.direct.login") != null && Boolean.parseBoolean(getValue("cas.auth.direct.login"))) {
			int countOfEnabledProviders = 0;
			if (isGoogleAuthEnabled()) {
				countOfEnabledProviders += 1;
			}
			if (isGitHubAuthEnabled()) {
				countOfEnabledProviders += 1;
			}
			if (isTwitterAuthEnabled()) {
				countOfEnabledProviders += 1;
			}
			if (isGlobusAuthEnabled()) {
				countOfEnabledProviders += 1;
			}
			if (countOfEnabledProviders == 1) {
				return true;
			}
		}
		return false;
	}
}
