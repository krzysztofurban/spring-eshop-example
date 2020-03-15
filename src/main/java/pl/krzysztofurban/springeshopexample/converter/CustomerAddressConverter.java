package pl.krzysztofurban.springeshopexample.converter;

import pl.krzysztofurban.springeshopexample.model.type.CustomerAddress;

import javax.persistence.AttributeConverter;

public class CustomerAddressConverter implements AttributeConverter<CustomerAddress, String> {
  private static final String SEPARATOR = ",";

  @Override
  public String convertToDatabaseColumn(CustomerAddress customerAddress) {
    if (customerAddress == null) {
      return "";
    }
    return customerAddress.getStreetAddress()
        + SEPARATOR
        + customerAddress.getCity()
        + SEPARATOR
        + customerAddress.getCountry();
  }

  @Override
  public CustomerAddress convertToEntityAttribute(String value) {
    CustomerAddress customerAddress = null;
    if (value != null && value.contains(SEPARATOR)) {
      String[] tokens = value.split(SEPARATOR);
      customerAddress = new CustomerAddress();
      customerAddress.setStreetAddress(tokens[0]);
      customerAddress.setCity(tokens[1]);
      customerAddress.setCountry(tokens[2]);
    }
    return customerAddress;
  }
}
