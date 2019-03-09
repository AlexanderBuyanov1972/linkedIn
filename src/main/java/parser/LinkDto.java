package parser;

import javax.xml.soap.Text;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Date;

public class LinkDto {


    String id;
    String source_id;
    String source;
    String source_link;
    String apply_link;
    String company;
    String location;
    String position;
    String employment_type;
    String relocation;
    LocalDateTime date_opened;
    LocalDateTime date_closed;
    String company_location;
    Array skills;
    Array education;
    String experience;
    String language;
    String processing_stages;
    Boolean manual_processing;
    Array sent_to;
    Text plain_text;

    public LinkDto(String id, String source_id, String source, String source_link, String apply_link, String company, String location, String position, String employment_type, String relocation, LocalDateTime date_opened, LocalDateTime date_closed, String company_location, Array skills, Array education, String experience, String language, String processing_stages, Boolean manual_processing, Array sent_to, Text plain_text) {
        this.id = id;
        this.source_id = source_id;
        this.source = source;
        this.source_link = source_link;
        this.apply_link = apply_link;
        this.company = company;
        this.location = location;
        this.position = position;
        this.employment_type = employment_type;
        this.relocation = relocation;
        this.date_opened = date_opened;
        this.date_closed = date_closed;
        this.company_location = company_location;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
        this.language = language;
        this.processing_stages = processing_stages;
        this.manual_processing = manual_processing;
        this.sent_to = sent_to;
        this.plain_text = plain_text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_link() {
        return source_link;
    }

    public void setSource_link(String source_link) {
        this.source_link = source_link;
    }

    public String getApply_link() {
        return apply_link;
    }

    public void setApply_link(String apply_link) {
        this.apply_link = apply_link;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployment_type() {
        return employment_type;
    }

    public void setEmployment_type(String employment_type) {
        this.employment_type = employment_type;
    }

    public String getRelocation() {
        return relocation;
    }

    public void setRelocation(String relocation) {
        this.relocation = relocation;
    }

    public LocalDateTime getDate_opened() {
        return date_opened;
    }

    public void setDate_opened(LocalDateTime date_opened) {
        this.date_opened = date_opened;
    }

    public LocalDateTime getDate_closed() {
        return date_closed;
    }

    public void setDate_closed(LocalDateTime date_closed) {
        this.date_closed = date_closed;
    }

    public String getCompany_location() {
        return company_location;
    }

    public void setCompany_location(String company_location) {
        this.company_location = company_location;
    }

    public Array getSkills() {
        return skills;
    }

    public void setSkills(Array skills) {
        this.skills = skills;
    }

    public Array getEducation() {
        return education;
    }

    public void setEducation(Array education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProcessing_stages() {
        return processing_stages;
    }

    public void setProcessing_stages(String processing_stages) {
        this.processing_stages = processing_stages;
    }

    public Boolean getManual_processing() {
        return manual_processing;
    }

    public void setManual_processing(Boolean manual_processing) {
        this.manual_processing = manual_processing;
    }

    public Array getSent_to() {
        return sent_to;
    }

    public void setSent_to(Array sent_to) {
        this.sent_to = sent_to;
    }

    public Text getPlain_text() {
        return plain_text;
    }

    public void setPlain_text(Text plain_text) {
        this.plain_text = plain_text;
    }
}
