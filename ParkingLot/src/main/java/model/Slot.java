package model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Slot {
    @NonNull
    Integer id;
    boolean occupied;
}