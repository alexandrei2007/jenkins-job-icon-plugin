package io.jenkins.plugins.jobicon;

import hudson.Extension;
import hudson.Util;
import hudson.util.ListBoxModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import jenkins.model.GlobalConfiguration;
import org.kohsuke.stapler.DataBoundSetter;

@Extension
public class JobIconGlobalConfiguration extends GlobalConfiguration 
{
    private List<Integer> sizes;
    private String iconSize;

    public static JobIconGlobalConfiguration get() {
        return GlobalConfiguration.all().get(JobIconGlobalConfiguration.class);
    }

    public JobIconGlobalConfiguration() {
        load();
    }

    private List<Integer> getSizes() 
    {
        if (sizes != null)
            return sizes;

        sizes = new ArrayList<Integer>();
        sizes.add(16);
        sizes.add(32);
        sizes.add(64);

        return sizes;
    }

    public String getIconSize() {
        return iconSize;
    }

    @DataBoundSetter
    public void setIconSize(String iconSize) {
        this.iconSize = Util.fixEmpty(iconSize);
        save();
    }

    @Nonnull
    @Override
    public String getDisplayName() {
        return "Job icon settings";
    }

    public ListBoxModel doFillIconSizeItems() {
        ListBoxModel sizes = new ListBoxModel();

        for (Integer size : this.getSizes()) 
        {
            String strSize = String.format("%dx%d", size, size);
            sizes.add(strSize, strSize);
        }

        return sizes;
    }

}