package io.jenkins.plugins.localization_no_country;

import hudson.Extension;
import hudson.PluginWrapper;
import io.jenkins.plugins.localization.support.LocalizationContributor;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Extension
public class LocalizationContributorNoCountry extends LocalizationContributor {
    private static final Logger logger
        = Logger.getLogger(LocalizationContributorNoCountry.class.getName());

    @CheckForNull
    @Override
    public URL getPluginResource(@Nonnull String resource,
                                 @Nonnull PluginWrapper plugin) {
        URL r = getClass().getClassLoader().getResource("plugin-resources/"
            + resource);
        if (r != null) {
            logger.log(Level.FINER, "Plugin resource requested: '"
                + resource + "', plugin resource found: '" + r.toString()
                + "'.");
        } else {
            logger.log(Level.FINE, "Plugin resource requested: '" + resource
                + "', no plugin resource found.");
        }
        return r;
    }

    @CheckForNull
    @Override
    public URL getResource(@Nonnull String resource) {
        if (resource.startsWith("/")) {
            resource = resource.substring(1);
        }
        URL r = getClass().getClassLoader().getResource(resource);
        if (r != null) {
            logger.log(Level.FINER, "Core resource requested: '"
                + resource + "', core resource found: '" + r.toString() + "'.");
        } else {
            Level logLevel;
            // Avoid logging non-existing localized CSS/images/bundles/scripts
            // at the FINE level
            if (resource.startsWith("css/")  || resource.startsWith("images/")
                || resource.startsWith("jsbundles/")
                || resource.startsWith("scripts/")) {
                logLevel = Level.FINER;
            } else {
                logLevel = Level.FINE;
            }
            logger.log(logLevel, "Core resource requested: '" + resource
                + "', no core resource found.");
        }
        return r;
    }
}
