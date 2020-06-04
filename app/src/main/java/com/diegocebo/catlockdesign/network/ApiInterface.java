package com.diegocebo.catlockdesign.network;

import com.diegocebo.catlockdesign.login.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface ApiInterface {

    @POST("usuarios/login")
    Call<Usuario> login(@Body Usuario usuario);

    @POST("usuarios/register")
    Call<Usuario> usuarioRegister(@Body Usuario usuario);

    @PUT("usuarios")
    Call<Usuario> updateUsuario(Usuario usuario);

}
