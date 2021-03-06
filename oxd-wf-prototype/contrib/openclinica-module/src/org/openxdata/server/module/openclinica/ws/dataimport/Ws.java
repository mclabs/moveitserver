
package org.openxdata.server.module.openclinica.ws.dataimport;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import org.openxdata.server.module.openclinica.ws.dataimport.ImportDataResponse;
import org.openxdata.server.module.openclinica.ws.dataimport.Ws;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0-b26-ea3
 * Generated source version: 2.0
 * 
 */
@WebService(name = "ws", targetNamespace = "http://openclinica.org/ws/dataImport/v1", wsdlLocation = "file:/H:/ws/dataImportWsdl.wsdl.xml")
@SOAPBinding(parameterStyle = ParameterStyle.BARE)
public interface Ws {


    /**
     * 
     * @param importDataRequest
     * @return
     *     returns org.openxdata.server.module.openclinica.ws.dataimport.ImportDataResponse
     */
    @WebMethod
    @WebResult(name = "importDataResponse", targetNamespace = "http://openclinica.org/ws/dataImport/v1", partName = "importDataResponse")
    public ImportDataResponse importData(
        @WebParam(name = "importDataRequest", targetNamespace = "http://openclinica.org/ws/dataImport/v1", partName = "importDataRequest")
        Object importDataRequest);

}
