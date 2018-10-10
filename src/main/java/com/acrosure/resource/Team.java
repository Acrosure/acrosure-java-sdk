package com.acrosure.resource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public class Team {
    private String id;
    private String companyName;
    private String callbackUrl;
    private boolean enableProduction;
    private boolean isInsurer;
    private String agentCode;
    private boolean policyExposed;
    private boolean appDataConcealed;
    private File logoFile;
    private User[] users;

    @JsonCreator
    public Team(
            @JsonProperty("id") String id,
            @JsonProperty("company_name") String companyName,
            @JsonProperty("callback_url") String callbackUrl,
            @JsonProperty("enable_production") boolean enableProduction,
            @JsonProperty("is_insurer") boolean isInsurer,
            @JsonProperty("agent_code") String agentCode,
            @JsonProperty("policy_exposed") boolean policyExposed,
            @JsonProperty("app_data_concealed") boolean appDataConcealed,
            @JsonProperty("logo_file") File logoFile,
            @JsonProperty("users") User[] users) {
        this.id = id;
        this.companyName = companyName;
        this.callbackUrl = callbackUrl;
        this.enableProduction = enableProduction;
        this.isInsurer = isInsurer;
        this.agentCode = agentCode;
        this.policyExposed = policyExposed;
        this.appDataConcealed = appDataConcealed;
        this.logoFile = logoFile;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public boolean isEnableProduction() {
        return enableProduction;
    }

    public boolean isInsurer() {
        return isInsurer;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public boolean isPolicyExposed() {
        return policyExposed;
    }

    public boolean isAppDataConcealed() {
        return appDataConcealed;
    }

    public File getLogoFile() {
        return logoFile;
    }

    public User[] getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                ", enableProduction=" + enableProduction +
                ", isInsurer=" + isInsurer +
                ", agentCode='" + agentCode + '\'' +
                ", policyExposed=" + policyExposed +
                ", appDataConcealed=" + appDataConcealed +
                ", logoFile=" + logoFile +
                ", users=" + Arrays.toString(users) +
                '}';
    }
}
