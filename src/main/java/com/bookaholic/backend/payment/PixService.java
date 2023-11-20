package com.bookaholic.backend.payment;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookaholic.backend.model.CobGerada;
import com.bookaholic.backend.model.ErrorResponse;
import com.bookaholic.backend.model.PayloadLocationQrCode;
import com.google.gson.Gson;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;


@Service
public class PixService {


    public JSONObject pixCreateEvp() throws EfiPayException {
		try {
            Credentials credentials = new Credentials();

		    JSONObject options = new JSONObject();
		    options.put("client_id", credentials.getClientId());
		    options.put("client_secret", credentials.getClientSecret());
		    options.put("certificate", credentials.getCertificate());
		    options.put("sandbox", credentials.isSandbox());
		
			EfiPay efi = new EfiPay(options);
			JSONObject response = efi.call("pixCreateEvp", new HashMap<String,String>(), new JSONObject());
      System.out.println(response.getString("chave"));
            return response;
		}catch (EfiPayException e){
            throw new RuntimeException(e.getMessage());
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
       
  }
  
  public CobGerada pixCreateImmediateCharge(ClienteDto cliente) throws JSONException, EfiPayException {
    try {
        Credentials credentials = new Credentials();

        JSONObject options = new JSONObject();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("certificate", credentials.getCertificate());
        options.put("sandbox", credentials.isSandbox());
  
      
        JSONObject body = new JSONObject();

        System.out.println(cliente.toString());
        /* 
          body.put("calendario", new JSONObject().put("expiracao", 3600));
          body.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
          body.put("valor", new JSONObject().put("original", "0.01"));
          body.put("chave", pixCreateEvp().getString("chave"));
          body.put("solicitacaoPagador", "Serviço realizado.");
  */      
          String cpf = cliente.cpf().replaceAll("\\.", "");
          cpf  =  cpf.replaceAll("-", "");

          String rg = cliente.rg().replaceAll("\\.", "");
          rg  =  rg.replaceAll("-", "");

          body.put("calendario", new JSONObject().put("expiracao", 3600));
          body.put("devedor", new JSONObject().put("cpf", cpf ).put("nome" , cliente.nome_completo()));
          body.put("valor", new JSONObject().put("original", "0.01"));
          body.put("chave", "3c4961f4-0921-4eda-97bc-71704defb09f");
    
      /* 
          JSONArray infoAdicionais = new JSONArray();
          infoAdicionais.put(new JSONObject().put("DataNascimento",""+ cliente.dataNasc()).put("valor", "Assinatura Bookaholic"));
          infoAdicionais.put(new JSONObject().put("RG", rg).put("valor", "Informação Adicional2 do PSP-Recebedor"));
          body.put("infoAdicionais", infoAdicionais);
  */
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateImmediateCharge", new HashMap<String,String>(), body);
            CobGerada res = new Gson().fromJson(response.toString() , CobGerada.class);
            return res;
          }catch (EfiPayException e){
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            throw new RuntimeException(e.getMessage());
          }
          catch (Exception e) {
            System.out.println(e.getMessage());
             throw new RuntimeException(e.getMessage());
          }
      }
    
    public PayloadLocationQrCode PixGenerateQRcode(Long id) {
        
	  Credentials credentials = new Credentials();

    HashMap<String, Object> options = new HashMap<String, Object>();
      options.put("client_id", credentials.getClientId());
      options.put("client_secret", credentials.getClientSecret());
      options.put("certificate", credentials.getCertificate());
      options.put("sandbox", credentials.isSandbox());

        HashMap<String, String> params = new HashMap<String, String>();
		    params.put("id", "" +id);

        try {
          EfiPay efi= new EfiPay(options);
          Map<String, Object> response = efi.call("pixGenerateQRCode", params, new HashMap<String, Object>());

          JSONObject resp = new JSONObject(response);

          PayloadLocationQrCode qrcode = new Gson().fromJson(resp.toString() , PayloadLocationQrCode.class);
     
          
          File outputfile = new File("qrCodeImage.png");
          ImageIO.write(ImageIO.read(new ByteArrayInputStream(javax.xml.bind.DatatypeConverter.parseBase64Binary(((String) response.get("imagemQrcode")).split(",")[1]))), "png", outputfile);
          System.out.println("File: "+ outputfile);
          System.out.println("Map: "+ response);
           
          return qrcode;
        }catch (EfiPayException e){
          System.out.println(e.getError());
          System.out.println(e.getErrorDescription());
          throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
          System.out.println(e.getMessage());
          throw new RuntimeException(e.getMessage());
        }
	  }
    
    public static String formatar(String dado) {
        String test ;
       test =  dado.replaceAll("\\.", "");
       test =  test.replaceAll("-", "");
       return test;
    }
    public void pixCreateCharge() {

            Credentials credentials = new Credentials();
            JSONObject options = new JSONObject(); 
            options.put("client_id", credentials.getClientId());
            options.put("client_secret", credentials.getClientSecret());
            options.put("certificate", credentials.getCertificate());
            options.put("sandbox", credentials.isSandbox());

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("txid", "7978c0c97ea847e78e8849634473c1f1");

            JSONObject body = new JSONObject();
            body.put("calendario", new JSONObject().put("expiracao", 3600));
            body.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
            body.put("valor", new JSONObject().put("original", "123.45"));
            body.put("chave", "Insira_aqui_sua_chave");
            body.put("solicitacaoPagador", "Serviço realizado.");

            JSONArray infoAdicionais = new JSONArray();
            infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
            infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
            body.put("infoAdicionais", infoAdicionais);

            try {
                EfiPay efi = new EfiPay(options);
                JSONObject response = efi.call("pixCreateCharge", params, body);
                System.out.println(response);

            }catch (EfiPayException e){
                System.out.println(e.getError());
                System.out.println(e.getErrorDescription());
            }
                catch (Exception e) {
                System.out.println(e.getMessage());
             }
    }
}
