package com.byclosure.maven.plugins.middleman;

import org.apache.commons.exec.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @goal install
 * @requiresProject true
 */
public class BundleInstallMojo extends AbstractJRubyMojo {

	/**
	 * @parameter default-value="Gemfile" expression="${bundler.gemfile}"
	 */
	protected String gemfile;

	@Override
	protected void executeComand() throws IOException {
		final File jrubyFile = new File(jruby_bin, "jruby");

		final Map<String, String> env = new HashMap<String, String>(System.getenv());
		env.put("GEM_HOME", gem_home);
		env.put("GEM_PATH", gem_path);

		final CommandLine cmd = getCrossPlatformCommandLine(
				new File(gem_home, new File("bin", "bundle").getPath()).getPath());
		cmd.addArgument("install");
		cmd.addArgument("--binstubs=" + binstubs);
		cmd.addArgument("--shebang=" + jrubyFile.getPath());
		cmd.addArgument("--gemfile=" + gemfile);



		executeCommandLine(cmd, env, project.getBasedir());
	}
}