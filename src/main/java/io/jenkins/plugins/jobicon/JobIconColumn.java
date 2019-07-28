package io.jenkins.plugins.jobicon;

import hudson.Extension;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

public class JobIconColumn extends ListViewColumn
{
    public String getIconSize() {
        JobIconGlobalConfiguration config = JobIconGlobalConfiguration.get();
        return config.getIconSize();
    }

    @Extension
    public static class JobIconColumnDescriptor extends ListViewColumnDescriptor
    {
        @Override
        public boolean shownByDefault() {
            return false;
        }

        @Override
        public String getDisplayName() {
			return "Icon";
        }

        @Override
        public ListViewColumn newInstance(StaplerRequest request, JSONObject formData) throws FormException {
            return new JobIconColumn();
        }
    }
}
