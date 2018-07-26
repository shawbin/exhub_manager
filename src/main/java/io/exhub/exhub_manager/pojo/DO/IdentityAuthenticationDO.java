package io.exhub.exhub_manager.pojo.DO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 身份认证
 * @date 2018/7/26
 * @author
 */
public class IdentityAuthenticationDO {

    /**
     * 0 审核未通过
     */
    public static final Byte ADUIT_NOT_PASS = 0;
    /**
     * 1 审核成功
     */
    public static final Byte ADUIT_PASS = 1;
    /**
     * 2 审核中
     */
    public static final Byte ADUITING = 2;
    /**
     * 3 未申请认证
     */
    public static final Byte NOT_AUTH = 3;
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.id
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.user_id
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.frist_name
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    @NotBlank(message = "参数不能为空")
    private String fristName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.last_name
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    @NotBlank(message = "参数不能为空")
    private String lastName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.country_region
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    @NotBlank(message = "参数不能为空")
    private String countryRegion;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.id_type
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    @NotNull(message = "参数不能为空")
    private Byte idType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.id_number
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    @NotBlank(message = "参数不能为空")
    private String idNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.face_photo
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    @NotBlank(message = "参数不能为空")
    private String facePhoto;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.back_photo
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    @NotBlank(message = "参数不能为空")
    private String backPhoto;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.signature_photo
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    @NotBlank(message = "参数不能为空")
    private String signaturePhoto;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.status
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.message
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    private String message;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.create_time
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_identity_authentication.update_time
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.id
     *
     * @return the value of t_identity_authentication.id
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.id
     *
     * @param id the value for t_identity_authentication.id
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.user_id
     *
     * @return the value of t_identity_authentication.user_id
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.user_id
     *
     * @param userId the value for t_identity_authentication.user_id
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.frist_name
     *
     * @return the value of t_identity_authentication.frist_name
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public String getFristName() {
        return fristName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.frist_name
     *
     * @param fristName the value for t_identity_authentication.frist_name
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setFristName(String fristName) {
        this.fristName = fristName == null ? null : fristName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.last_name
     *
     * @return the value of t_identity_authentication.last_name
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.last_name
     *
     * @param lastName the value for t_identity_authentication.last_name
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.country_region
     *
     * @return the value of t_identity_authentication.country_region
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public String getCountryRegion() {
        return countryRegion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.country_region
     *
     * @param countryRegion the value for t_identity_authentication.country_region
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion == null ? null : countryRegion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.id_type
     *
     * @return the value of t_identity_authentication.id_type
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public Byte getIdType() {
        return idType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.id_type
     *
     * @param idType the value for t_identity_authentication.id_type
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setIdType(Byte idType) {
        this.idType = idType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.id_number
     *
     * @return the value of t_identity_authentication.id_number
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.id_number
     *
     * @param idNumber the value for t_identity_authentication.id_number
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.face_photo
     *
     * @return the value of t_identity_authentication.face_photo
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public String getFacePhoto() {
        return facePhoto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.face_photo
     *
     * @param facePhoto the value for t_identity_authentication.face_photo
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setFacePhoto(String facePhoto) {
        this.facePhoto = facePhoto == null ? null : facePhoto.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.back_photo
     *
     * @return the value of t_identity_authentication.back_photo
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public String getBackPhoto() {
        return backPhoto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.back_photo
     *
     * @param backPhoto the value for t_identity_authentication.back_photo
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setBackPhoto(String backPhoto) {
        this.backPhoto = backPhoto == null ? null : backPhoto.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.signature_photo
     *
     * @return the value of t_identity_authentication.signature_photo
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public String getSignaturePhoto() {
        return signaturePhoto;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.signature_photo
     *
     * @param signaturePhoto the value for t_identity_authentication.signature_photo
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setSignaturePhoto(String signaturePhoto) {
        this.signaturePhoto = signaturePhoto == null ? null : signaturePhoto.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.status
     *
     * @return the value of t_identity_authentication.status
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.status
     *
     * @param status the value for t_identity_authentication.status
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.message
     *
     * @return the value of t_identity_authentication.message
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.message
     *
     * @param message the value for t_identity_authentication.message
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.create_time
     *
     * @return the value of t_identity_authentication.create_time
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.create_time
     *
     * @param createTime the value for t_identity_authentication.create_time
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_identity_authentication.update_time
     *
     * @return the value of t_identity_authentication.update_time
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_identity_authentication.update_time
     *
     * @param updateTime the value for t_identity_authentication.update_time
     *
     * @mbg.generated Tue Jul 24 18:22:31 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}