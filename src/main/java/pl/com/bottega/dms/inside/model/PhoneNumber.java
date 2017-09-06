package pl.com.bottega.dms.inside.model;

import javax.persistence.Embeddable;

@Embeddable
public class PhoneNumber {

    private String countryCode;

    private String number;

}