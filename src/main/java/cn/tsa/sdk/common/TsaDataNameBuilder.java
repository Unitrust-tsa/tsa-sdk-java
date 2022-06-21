package cn.tsa.sdk.common;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author JenphyJohn
 */
public class TsaDataNameBuilder implements DataNameBuilder {

    private static final String TSA_RESPONSE = "tsa_response";

    @Override
    public String build(String method) {
        return TSA_RESPONSE;
    }
}
