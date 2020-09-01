package v2;

import java.util.List;

public class Numeral {

    /*
    @member extract = regex @param value.replace(@member system.getSystemID(); <blank_string>"
     */

    private String          extract;
    private NumeralSystem   system;

    /* Process to build numeral object goes as follows
     * 1. Set the System Type, @method setSystem(String value);
     * 2. Set the extracted string from @param value, setExtract(String value);
     * 3. Validate the string based off @member system constraints, validate().
     */

    public Numeral (String value) throws NumeralException {
        setSystem (value);
        setExtract(value);
        validate();
    }

    /*@method validate: executes self-validation measures checking comparing each character & length to our @member
     *system.getAllowedCharacters() & system.getLengthLimit()
     */

    private void validate() throws NumeralException {
        List<Character> chars = system.getAllowedCharacters();
        for(char c : extract.toCharArray()) {
            if(!chars.contains(c)) {
                throw new NumeralException();
            }
        }
    }

    /* @method setSystem() loops through @NumeralSystem.values calling .getID() and checking if the value ends with
     * the ID.
     */

    private void setSystem (String value) {
        for(NumeralSystem system : NumeralSystem.values()) {
            if(value.endsWith(system.getID())) {
                this.system = system;
                break;
            }
        }
    }

    /*
    @method setExtract() replaces .getID() in @value to get an extracted string.
     */

    private void setExtract(String value) {
        this.extract = value.replace(system.getID(), "");
    }
}
