package pl.com.bottega.dms.model;

import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {

    private String countryCode;

    private String number;

}