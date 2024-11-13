package com.api.scm.forms;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder.Default;

@Getter
@Setter
public class ContactSearchForm {
    
    private String field="name";
    private String value;

}