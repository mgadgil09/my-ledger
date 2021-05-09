package com.myledger.model.urja;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(indexes = {@Index(name = "enterprise_name", columnList = "enterprise_name", unique = true)})
public class Enterprise {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Column(name = "enterprise_name")
    private String enterpriseName;

    public long getId() {
        return id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

}
