package akoletter.devakoletterapi.jpa.filemst.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int fileId;
}
