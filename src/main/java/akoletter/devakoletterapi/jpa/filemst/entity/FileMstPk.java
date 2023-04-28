package akoletter.devakoletterapi.jpa.filemst.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMstPk implements Serializable {
  private static final long serialVersionUID = -3707559740976324731L;
  @Column(name = "file_id")
  private int fileId;
}
