package com.matdzie.peopleimportapi.api.v1.model;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class PersonListDto {

    @NotEmpty
    List<PersonDto> results;

    public PersonListDto(List<PersonDto> results) {
        this.results = results;
    }

    public List<PersonDto> getResults() {
        return results;
    }

    public void setResults(List<PersonDto> results) {
        this.results = results;
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonListDto that = (PersonListDto) o;
        return results.equals(that.results);
    }
}
