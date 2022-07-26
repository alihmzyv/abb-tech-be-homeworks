import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    void toStringShouldReturnCorrectData() {
        Species dog1 = Species.DOG;
        dog1.hasFur = false;
        Pet pet1 = new Pet(Species.DOG, "Toplan", 2, 33, new String[]{"barking"});

        //expected String to be returned
        String expected = String.format("%s{nickname=%s: Can fly: %s, Has %d legs, Has fur: %s; age=%d, tricklevel=%d, habits=%s}",
                pet1.getSpecies(), pet1.getNickname(), pet1.getSpecies().canFly, pet1.getSpecies().numberOfLegs, pet1.getSpecies().hasFur,
                pet1.getAge(), pet1.getTrickLevel(), Arrays.toString(pet1.getHabits()));

        assertEquals(expected, pet1.toString());
    }
}