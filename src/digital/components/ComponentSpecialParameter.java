package digital.components;

/**
 *
 * @author AMAUROTE
 */
public class ComponentSpecialParameter {
    
    private final String name;
    private int parameterType;
    private int value;

    public ComponentSpecialParameter(String name, int parameterType, int value) {
        this.name = name;
        
        /*
            0 = boolean
            1 = unsigned int
         */
        
        if (parameterType <= 0) {
            this.parameterType = 0;
        }
        if (parameterType >= 1) {
            this.parameterType = 1;
        }

        setValue(value);
    }

    public void setValue(int value) {
        switch (parameterType) {
            case 0:
                this.value = (parameterType <= 0) ? 0 : 1;
                break;
            case 1:
                this.value = (parameterType <= 0) ? 0 : value;
                break;
            default:
                parameterType = 1;
                setValue(value);
                break;
        }
    }
    
    public int getParameterType() {
        return parameterType;
    }
    
    public int getValue() {
        return value;
    }
}
