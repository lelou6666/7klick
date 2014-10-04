package com.sevenklick.common.util.domain;

/**
 * Created by pierre.petersson on 26/05/2014.
 */
public class UserContext {
        public Long getPlcId() {
            return plcId;
        }

        public void setPlcId(Long plcId) {
            this.plcId = plcId;
        }


        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getLangCode() {
            return langCode;
        }

        public void setLangCode(String langCode) {
            this.langCode = langCode;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(String roleCode) {
            this.roleCode = roleCode;
        }

    public Boolean getAdminEnabled() {
        return adminEnabled;
    }

    public void setAdminEnabled(Boolean adminEnabled) {
        this.adminEnabled = adminEnabled;
    }

    Long plcId;
        String userName;
        String langCode;
        String countryCode;
        String roleCode;
        Boolean adminEnabled=false;

}
