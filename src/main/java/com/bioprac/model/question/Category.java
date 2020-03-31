package com.bioprac.model.question;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="category", length = 25)
    private String category;

    @NotBlank
    @Column(name="category_key", length = 25)
    private String categoryKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }
}
