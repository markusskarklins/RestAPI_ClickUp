package com.apitest;

import io.cucumber.java.After;

public class Hooks {

  @After
  public void afterHook() {
      String folderId = StepDefinitions.getFolderId();  // Use the getter method to access folderId
      if (folderId != null) {
          ClickUpClient.deleteFolder(folderId);
      }
  }

}
