/*
 *
 * Copyright (c) 2018 - 2018 Worksoft, Inc.
 *
 * ${CLASS_NAME}
 *
 * @author rrinehart on 9/14/2018
 */

package com.worksoft.jenkinsci.plugins.em;

import com.worksoft.jenkinsci.plugins.em.config.ExecutionManagerConfig;
import com.worksoft.jenkinsci.plugins.em.model.ExecutionManagerServer;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.ComboBoxModel;
import jenkins.model.GlobalConfiguration;
import jenkins.tasks.SimpleBuildStep;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.io.IOException;

public class ExecuteRequest extends Builder implements SimpleBuildStep {

  public final String bookmark;
  public final String request;

  private ExecutionManagerConfig config;

  @DataBoundConstructor
  public ExecuteRequest (String request, String bookmark) {
    this.bookmark = bookmark;
    this.request = request;
    config = GlobalConfiguration.all().get(ExecutionManagerConfig.class);
  }

  @Override
  public void perform (@Nonnull Run<?, ?> run, @Nonnull FilePath workspace, @Nonnull Launcher launcher, @Nonnull TaskListener listener) throws InterruptedException, IOException {
    ExecutionManagerServer server = config.getEmServer();
  }

  @Extension
  public static final class ExecutionManagerBuilderDescriptor extends BuildStepDescriptor<Builder> {

    @Override
    public boolean isApplicable (Class<? extends AbstractProject> jobType) {
      return true;
    }

    @Override
    public String getDisplayName () {
      return "Run Execution Manager Request";
    }

    public ComboBoxModel doFillRequestItems () {
      return new ComboBoxModel("Apple", "Banana", "Oreo");
    }

    public ComboBoxModel doFillBookmarkItems () {
      return new ComboBoxModel("One", "Two", "Three");
    }


  }
}