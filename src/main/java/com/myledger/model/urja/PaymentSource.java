package com.myledger.model.urja;
import javax.persistence.*;

@Entity
@Table(indexes = {@Index(name = "source_name", columnList = "source_name", unique = true)})
public class PaymentSource {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "source_name")
    private String sourceName;
    @Column(name = "source_code")
    private String sourceCode;

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public long getId() {
        return id;
    }


    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
}
