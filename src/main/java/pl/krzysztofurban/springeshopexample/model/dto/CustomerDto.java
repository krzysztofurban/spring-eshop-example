package pl.krzysztofurban.springeshopexample.model.dto;

public class CustomerDto {
  private Long id;
  private String name;

  public CustomerDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "CustomerDto{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
