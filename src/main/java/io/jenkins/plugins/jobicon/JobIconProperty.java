package io.jenkins.plugins.jobicon;

import hudson.Extension;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.model.JobPropertyDescriptor;
import hudson.util.*;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

public class JobIconProperty extends JobProperty<Job<?, ?>>
{
    private Boolean useIcon;
    private String iconUrl;
    private String cssClassName;

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String value) {
        this.iconUrl = value;
    }

    public String getCssClassName() {
        return this.cssClassName;
    }

    public void setCssClassName(String value) {
        this.cssClassName = value;
    }

    public Boolean getUseIcon() {
        return this.useIcon;
    }

    public void setUseIcon(Boolean value) {
        this.useIcon = value;
    }

	@DataBoundConstructor
	public JobIconProperty(Boolean useIcon, String iconUrl, String cssClassName)
	{
        this.useIcon = useIcon;
        this.iconUrl = iconUrl;
        this.cssClassName = cssClassName;
	}

	@Extension
	public static final class DescriptorImpl extends JobPropertyDescriptor
	{
		@Override
		public boolean isApplicable(Class<? extends Job> jobType)
		{
			return true;
		}

		@Override
		public JobIconProperty newInstance(StaplerRequest req,
			JSONObject formData) throws FormException
		{
            String iconUrl = null;
            JSONObject json = formData.getJSONObject( "useIcon" );
            if ( ( json != null ) && !( json.isNullObject() ) ) {
                iconUrl = json.getString( "iconUrl" );
                json.put("useIcon", true);
            } else {
                json = null;
            }
            
            if (!this.validateUrl(iconUrl)) { 
                throw new FormException(String.format(Messages.JobIconProperty_DescriptorImpl_errors_invalidUrl(), iconUrl), "iconUrl");
            }

            return req.bindJSON(JobIconProperty.class, json);
        }

        public FormValidation doCheckIconUrl(@QueryParameter String value) 
        {
            if (!this.validateUrl(value)) { 
                return FormValidation.error(String.format(Messages.JobIconProperty_DescriptorImpl_errors_invalidUrl(), value));
           }
           
           return FormValidation.ok();
       }

       private Boolean validateUrl(String url) 
       {
            if (url != null && url.length() > 0) { 
                if (!url.matches("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|png|svg)")){
                    return false;
                }
            }

            return true;
       }

       public ListBoxModel doFillCssClassNameItems() {
           ListBoxModel items = new ListBoxModel();
           items.add("Default", "");
           items.add("Grayscale", "jobicon-grayscale");
           items.add("Hue", "jobicon-hue");
           items.add("Invert", "jobicon-invert");
   
           return items;
       }
	}
}