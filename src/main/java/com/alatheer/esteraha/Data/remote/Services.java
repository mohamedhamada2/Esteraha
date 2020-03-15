package com.alatheer.esteraha.Data.remote;

import com.alatheer.esteraha.Data.remote.Models.AddClient;
import com.alatheer.esteraha.Data.remote.Models.AddEstrahat;
import com.alatheer.esteraha.Data.remote.Models.AddHagz;
import com.alatheer.esteraha.Data.remote.Models.AllRemains;
import com.alatheer.esteraha.Data.remote.Models.AllReservationModel;
import com.alatheer.esteraha.Data.remote.Models.CheckClient;
import com.alatheer.esteraha.Data.remote.Models.CheckDate;
import com.alatheer.esteraha.Data.remote.Models.Erad;
import com.alatheer.esteraha.Data.remote.Models.Esal;
import com.alatheer.esteraha.Data.remote.Models.EsalView;
import com.alatheer.esteraha.Data.remote.Models.Feat;
import com.alatheer.esteraha.Data.remote.Models.Login;
import com.alatheer.esteraha.Data.remote.Models.Masrof;
import com.alatheer.esteraha.Data.remote.Models.MasrofatReport;
import com.alatheer.esteraha.Data.remote.Models.Mortag3View;
import com.alatheer.esteraha.Data.remote.Models.Nationality;
import com.alatheer.esteraha.Data.remote.Models.OrdersModel;
import com.alatheer.esteraha.Data.remote.Models.RKM;
import com.alatheer.esteraha.Data.remote.Models.RestModel;
import com.alatheer.esteraha.Data.remote.Models.Success;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Services {
    @FormUrlEncoded
    @POST("Api/login")
    Call<Login>admin_login(@Field("user_name")String user_name,@Field("password")String password);
    @GET("Api/get_nationality")
    Call<List<Nationality>>getNationality();
    @FormUrlEncoded
    @POST("Api/add_client")
    Call<AddClient>add_client(@Field("national_num")String national_num,@Field("name")String name,@Field("reserved")String reserved
    ,@Field("nationality")String nationality,@Field("mob")String mob,@Field("publisher")String publisher);
    @GET("Api/get_estrahat")
    Call<List<RestModel>>get_Rests();
    @FormUrlEncoded
    @POST("Api/add_hagz")
    Call<AddHagz>add_hagz(@Field("client_national_num") String national_num,@Field("estraha_id_fk")String estraha_id_fk,@Field("num_days")String num_days
    ,@Field("from_date")String from_date,@Field("to_date")String to_date
    ,@Field("total_value")String total_value,@Field("paid")String paid,@Field("remain")String remain
    ,@Field("discount_value")String discount_value,@Field("khasm")String khasm
    ,@Field("client_name")String client_name,@Field("mob")String MOB
    ,@Field("publisher")String publisher
    ,@Field("type_reserve")String type_reserve
    ,@Field("pay_method")String pay_method
    ,@Field("tamin")String tamin);
    @FormUrlEncoded
    @POST("Api/update_hagazats")
    Call<AddHagz>update_hagz(@Field("id")String id,@Field("client_national_num") String national_num,@Field("estraha_id_fk")String estraha_id_fk,@Field("num_days")String num_days
            ,@Field("khasm")String khasm,@Field("value")String value
            ,@Field("discount_value")String discount_value,@Field("paid")String paid,@Field("remain")String remain
            ,@Field("pay_method")String pay_method,@Field("type_reserve")String type_reserve,@Field("tamin")String tamin
            ,@Field("from_date")String from_date,@Field("to_date")String to_date
            ,@Field("name")String name
            ,@Field("mob")String mob
            ,@Field("publisher")String publisher);
    @FormUrlEncoded
    @POST("Api/delete_hagz")
    Call<AddHagz>delete_hagz(@Field("id")String id);
    @FormUrlEncoded
    @POST("Api/check_national_num")
    Call<CheckClient>check_user(@Field("client_national_num") String client_national_num);
    @FormUrlEncoded
    @POST("Api/hagz_day")
    Call<AllReservationModel>get_hagz_day(@Field("role_id_fk")String role_id_fk,@Field("publisher")String publisher);
    @FormUrlEncoded
    @POST("Api/all_hagz")
    Call<AllReservationModel>get_all_hagz(@Field("role_id_fk")String role_id_fk,@Field("publisher")String publisher);
    @FormUrlEncoded
    @POST("Api/get_all_khasm")
    Call<OrdersModel>get_all_orders(@Field("approved")String approved);
    @GET("Api/hagz_temporary")
    Call<OrdersModel>get_all_hagz_temporary();
    @GET("Api/all_clients")
    Call<List<CheckClient>>Get_Clients();
    @FormUrlEncoded
    @POST("Api/update_client")
    Call<AddClient>update_client(@Field("national_num")String national_num,@Field("name")String name,@Field("reserved")String reserved
            ,@Field("nationality")String nationality,@Field("mob")String mob,@Field("publisher")String publisher,@Field("id")String id);
    @FormUrlEncoded
    @POST("Api/delete_client")
    Call<AddClient>delete_client(@Field("id")String id);
    @FormUrlEncoded
    @POST("Api/search_by_name")
    Call<List<CheckClient>>Search_for_User(@Field("name")String name);
    @FormUrlEncoded
    @POST("Api/add_estrahat")
    Call<AddEstrahat>addrest(@Field("name")String name,@Field("adress")String adress,
                             @Field("price")String price,@Field("time_login")String time_login,
                             @Field("time_logout")String time_logout,@Field("details")String details,
                             @Field("lat_map")String lat_map,@Field("long_map")String long_map,
                             @Field("publisher")String publisher,
                             @Field("rkm")String rkm);
    @FormUrlEncoded
    @POST("Api/update_estrahat")
    Call<AddEstrahat>updaterest(@Field("id")String id,@Field("name")String name,@Field("adress")String adress,
                             @Field("price")String price,@Field("time_login")String time_login,
                             @Field("time_logout")String time_logout,@Field("details")String details,
                             @Field("lat_map")String lat_map,@Field("long_map")String long_map,
                             @Field("publisher")String publisher,
                             @Field("rkm")String rkm);
    @FormUrlEncoded
    @POST("Api/delete_estrahat")
    Call<AddEstrahat>deleterest(@Field("id")String id);
    @FormUrlEncoded
    @POST("Api/report_erad")
    Call<Erad>get_all_eradat(@Field("from_date")String from_date,@Field("to_date")String to_date,
                             @Field("role_id_fk")String role_id_fk,@Field("publisher")String publisher);

    @GET("Api/get_fea")
    Call<List<Feat>>get_feat();
    @FormUrlEncoded
    @POST("Api/add_masrof")
    Call<Success>add_masrof(@Field("masrofat_value")String masrofat_value,@Field("date_esal")String date_esal,
                            @Field("fe2a")String fe2a,@Field("publisher")String publisher);
    @FormUrlEncoded
    @POST("Api/get_masrofat")
    Call<List<Masrof>>get_masrofat(@Field("role_id_fk")String role_id_fk,@Field("publisher")String publisher);
    @FormUrlEncoded
    @POST("Api/getmasrofat")
    Call<MasrofatReport>get_masrofat_report(@Field("from_date")String from_date,@Field("to_date")String to_date,
                                            @Field("role_id_fk")String role_id_fk,@Field("publisher")String publisher);
    @FormUrlEncoded
    @POST("Api/get_date_to")
    Call<CheckDate>CheckDate(@Field("num_days")String num_days,@Field("estraha_id_fk")String estraha_id_fk,
                             @Field("national_num")String national_num,@Field("from_date")String from_date);
    @FormUrlEncoded
    @POST("Api/get_data_esal")
    Call<Esal>getEslatofUser(@Field("client_national_num")String client_national_num);
    @FormUrlEncoded
    @POST("Api/view_data_esal")
    Call<EsalView>getEslat(@Field("role_id_fk")String role_id_fk,@Field("publisher")String publisher);
    @FormUrlEncoded
    @POST("Api/add_esal")
    Call<Success>add_esal(@Field("rkm_esal")String rkm_esal
            ,@Field("reserve_id_fk")String reserve_id_fk
            ,@Field("client_nationl_num")String client_nationl_num,@Field("estraha_id_fk")String estraha_id_fk
            ,@Field("total_value")String total_value,@Field("pay_method")String pay_method
            ,@Field("paid")String paid,@Field("remain")String remain,@Field("date_esal")String date_esal
            ,@Field("publisher")String publisher);
    @GET("Api/get_last_rakm_esal")
    Call<RKM>get_esal_num();
    @GET("Api/get_last_rkm_mortaga")
    Call<RKM>get_mortag3_num();
    @FormUrlEncoded
    @POST("Api/get_data_mortag3at")
    Call<Esal>getMortag3ofUser(@Field("client_national_num")String client_national_num);
    @FormUrlEncoded
    @POST("Api/add_mortagaat")
    Call<Success>add_mortag3(@Field("rkm_esal")String rkm_esal,@Field("reserve_id_fk")String reserve_id_fk,
                             @Field("client_nationl_num")String client_nationl_num,
                             @Field("estraha_id_fk")String estraha_id_fk,
                             @Field("total_value")String total_value,
                             @Field("pay_method")String pay_method,
                             @Field("paid")String paid,
                             @Field("return_value")String return_value,
                             @Field("remain")String remain,
                             @Field("date_esal")String date_esal,
                             @Field("publisher")String publisher);
    @FormUrlEncoded
    @POST("Api/get_esal_remains")
    Call<AllRemains>get_remains(@Field("id")String id);
    @FormUrlEncoded
    @POST("Api/get_mortaga3_remains")
    Call<AllRemains>get_mortaga3at(@Field("id")String id);
    @FormUrlEncoded
    @POST("Api/change_temporry")
    Call<Success>change_temporry(@Field("id")String id);
    @FormUrlEncoded
    @POST("Api/accept_refuse")
        Call<OrdersModel>accept_refuse(@Field("approved")String approved,@Field("id")String id);
    @FormUrlEncoded
    @POST("Api/view_data_mortaga3at")
    Call<Mortag3View>getMortag3at(@Field("role_id_fk")String role_id_fk, @Field("publisher")String publisher);

}
