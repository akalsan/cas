package edu.emory.cci.aiw.cvrg.eureka.cas.oauth.provider;

import org.eurekaclinical.scribeupext.provider.SSLTwitterProvider;
import org.eurekaclinical.scribeupext.provider.Google2Provider;
import org.eurekaclinical.scribeupext.provider.GitHubProvider;
import org.eurekaclinical.scribeupext.provider.GlobusProvider;
import edu.emory.cci.aiw.cvrg.eureka.cas.ApplicationProperties;
import java.util.ArrayList;
import java.util.List;
import org.scribe.up.provider.OAuthProvider;

/**
 *
 * @author Andrew Post
 */
public final class OAuthProviders {
	private final List<OAuthProvider> providers;

	public OAuthProviders() {
		this.providers = new ArrayList<OAuthProvider>();
		ApplicationProperties applicationProperties = new ApplicationProperties();
		if (applicationProperties.isTwitterAuthEnabled()) {
			SSLTwitterProvider twitterProvider = new SSLTwitterProvider();
			twitterProvider.setKey(applicationProperties.getTwitterKey());
			twitterProvider.setSecret(applicationProperties.getTwitterSecret());
			this.providers.add(twitterProvider);
		}
		if (applicationProperties.isGoogleAuthEnabled()) {
			Google2Provider googleProvider = new Google2Provider();
			googleProvider.setKey(applicationProperties.getGoogleKey());
			googleProvider.setSecret(applicationProperties.getGoogleSecret());
			this.providers.add(googleProvider);
		}
		if (applicationProperties.isGitHubAuthEnabled()) {
			GitHubProvider gitHubProvider = new GitHubProvider();
			gitHubProvider.setKey(applicationProperties.getGitHubKey());
			gitHubProvider.setSecret(applicationProperties.getGitHubSecret());
			this.providers.add(gitHubProvider);
		}
		if (applicationProperties.isGlobusAuthEnabled()) {
			GlobusProvider globusProvider = new GlobusProvider();
			globusProvider.setKey(applicationProperties.getGlobusKey());
			globusProvider.setSecret(applicationProperties.getGlobusSecret());
			this.providers.add(globusProvider);
		}
	}
	
	public List<OAuthProvider> getProviders() {
		return this.providers;
	}
}
