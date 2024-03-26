package model;
import com.google.gson.annotations.SerializedName;

public class Patient {
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("middleName")
    private String middleName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("dob")
    private String dob;

    @SerializedName("patGender")
    private String patGender;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("mrn")
    private String mrn;

    @SerializedName("cmrn")
    private String cmrn;

    @SerializedName("eid")
    private String eid;

    @SerializedName("passportNumber")
    private String passportNumber;

    @SerializedName("mrnAssigningAuthority")
    private Authority mrnAssigningAuthority;

    @SerializedName("maritalStatus")
    private MaritalStatus maritalStatus;

    @SerializedName("race")
    private Race race;

    @SerializedName("language")
    private Language language;

    @SerializedName("nationality")
    private Nationality nationality;

    @SerializedName("address")
    private Address address;


    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPatGender() {
        return patGender;
    }

    public void setPatGender(String patGender) {
        this.patGender = patGender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMrn() {
        return mrn;
    }

    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    public String getCmrn() {
        return cmrn;
    }

    public void setCmrn(String cmrn) {
        this.cmrn = cmrn;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // ... (getter and setter methods for other attributes)

    public Authority getMrnAssigningAuthority() {
        return mrnAssigningAuthority;
    }

    public void setMrnAssigningAuthority(Authority mrnAssigningAuthority) {
        this.mrnAssigningAuthority = mrnAssigningAuthority;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    // ... (getter and setter methods for other associations)

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

