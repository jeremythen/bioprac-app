package com.bioprac.model.question;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="subcategory", length = 25)
    private String subcategory;

    @NotBlank
    @Column(name="subcategory_key", length = 25)
    private String subcategoryKey;

    @NotBlank
    @Column(name="category", length = 25)
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getSubcategoryKey() {
        return subcategoryKey;
    }

    public void setSubcategoryKey(String subcategoryKey) {
        this.subcategoryKey = subcategoryKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
