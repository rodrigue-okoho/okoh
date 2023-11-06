package com.okoho.okoho.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * A Personnel.
 */
@Document(collection = "personnel")
@TypeAlias("personnel")
public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @DBRef
    @Field("userAccount")
    private UserAccount userAccount;
    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Personnel id(String id) {
        this.id = id;
        return this;
    }
    public UserAccount getUserAccount() {
        return this.userAccount;
    }

    public Personnel userAccount(UserAccount userAccount) {
        this.setUserAccount(userAccount);
        return this;
    }
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Personnel)) {
            return false;
        }
        return id != null && id.equals(((Personnel) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Personnel{" +
            "id=" + getId() +
            "account"+getUserAccount().toString()+
            "}";
    }
}
