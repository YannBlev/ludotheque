package fr.eni.ludotheque.bo.dto;

import fr.eni.ludotheque.bo.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RetourLocationDTO {
    private List<String> codebarres=new ArrayList<String>();
}
