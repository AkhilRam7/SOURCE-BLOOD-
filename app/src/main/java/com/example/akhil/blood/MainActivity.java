package com.example.akhil.blood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_BLUE;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {



    ArrayList<Marker> markerPoints = new ArrayList<Marker>();
    ArrayList<Marker> dmarkerPoints = new ArrayList<Marker>();
    String defaultjson = "{\"index_name\":\"fced6df9-a360-4e08-8ca0-f283fc74ce15\",\"title\":\"Blood Bank Directory (updated till last month)\",\"desc\":\"\",\"created\":1507359745,\"updated\":1507359745,\"visualizable\":\"1\",\"source\":\"data.gov.in\",\"org_type\":\"Central\",\"org\":[\"Ministry of Health and Family Welfare\",\"Department of Health and Family Welfare\",\"National Institute of Health and Family Welfare (NIHFW) New Delhi\"],\"sector\":[\"Health and Family welfare\",\"Health\"],\"status\":\"ok\",\"message\":\"Resource detail\",\"total\":50,\"count\":50,\"limit\":\"150\",\"offset\":0,\"fields\":[{\"name\":\"sr_no\",\"type\":\"keyword\"},{\"name\":\"_blood_bank_name\",\"type\":\"keyword\"},{\"name\":\"_state\",\"type\":\"keyword\"},{\"name\":\"_district\",\"type\":\"keyword\"},{\"name\":\"_city\",\"type\":\"keyword\"},{\"name\":\"_address\",\"type\":\"keyword\"},{\"name\":\"pincode\",\"type\":\"keyword\"},{\"name\":\"_contact_no\",\"type\":\"keyword\"},{\"name\":\"_mobile\",\"type\":\"keyword\"},{\"name\":\"_helpline\",\"type\":\"keyword\"},{\"name\":\"_fax\",\"type\":\"keyword\"},{\"name\":\"_email\",\"type\":\"keyword\"},{\"name\":\"_website\",\"type\":\"keyword\"},{\"name\":\"_nodal_officer_\",\"type\":\"keyword\"},{\"name\":\"_contact_nodal_officer\",\"type\":\"keyword\"},{\"name\":\"_mobile_nodal_officer\",\"type\":\"keyword\"},{\"name\":\"_email_nodal_officer\",\"type\":\"keyword\"},{\"name\":\"_qualification_nodal_officer\",\"type\":\"keyword\"},{\"name\":\"_category\",\"type\":\"keyword\"},{\"name\":\"_blood_component_available\",\"type\":\"keyword\"},{\"name\":\"_apheresis\",\"type\":\"keyword\"},{\"name\":\"_service_time\",\"type\":\"keyword\"},{\"name\":\"_license_\",\"type\":\"keyword\"},{\"name\":\"_date_license_obtained\",\"type\":\"keyword\"},{\"name\":\"_date_of_renewal\",\"type\":\"keyword\"},{\"name\":\"_latitude\",\"type\":\"keyword\"},{\"name\":\"_longitude\",\"type\":\"keyword\"}],\"records\":[{\"sr_no\":\"1943\",\"_blood_bank_name\":\"CANCER INSTITUTE HOSPITAL BLOOD BANK\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Adyar\",\"_address\":\"Cancer Institute (WIA) ,No.18 Sardar Patel road\",\"pincode\":\"600036\",\"_contact_no\":\"044 22209150,044 24910754,044 22350241\",\"_mobile\":\"\",\"_helpline\":\"\",\"_fax\":\"044 24912085\",\"_email\":\"cibloodbank3@gmail.com\",\"_website\":\"http:\\/\\/www.cancerinstitutewia.org\",\"_nodal_officer_\":\"Dr. Narmadha\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"9840037100\",\"_email_nodal_officer\":\"drnarmadha01@gmail.com\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Charity\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24X7\",\"_license_\":\"126\",\"_date_license_obtained\":\"5\\/13\\/1997\",\"_date_of_renewal\":\"31\\/12\\/2017\",\"_latitude\":\"13.005335\",\"_longitude\":\"80.239623\"},{\"sr_no\":\"1944\",\"_blood_bank_name\":\"Rajiv Gandhi Government General Hospital\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"General Hospital Road\\r\\n2359,Park Town,\\r\\n2360,Chennai, Tamil Nadu \",\"pincode\":\"600003\",\"_contact_no\":\"044 25305711, 044 2530 5000\",\"_mobile\":\"9436120190\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"dchsub@yahoo.co.in\",\"_website\":\"http:\\/\\/www.mmc.tn.gov.in\",\"_nodal_officer_\":\"Dr.Subash\",\"_contact_nodal_officer\":\"044 25305711, 044 2530 5000\",\"_mobile_nodal_officer\":\"9381715141, 9443988432\",\"_email_nodal_officer\":\"dchsub@yahoo.co.in\",\"_qualification_nodal_officer\":\"MBBS, MD (Transfusion Medicine)\",\"_category\":\"Government\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24X7\",\"_license_\":\"36\\/28c\",\"_date_license_obtained\":\"N\\/A\",\"_date_of_renewal\":\"6.4.2018\",\"_latitude\":\"13.081279\",\"_longitude\":\"80.27678\"},{\"sr_no\":\"1945\",\"_blood_bank_name\":\"The Institute of Child Health and Hospital for Children\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Halls Road, Egmore, Chennai\",\"pincode\":\"600008\",\"_contact_no\":\"044 28191132 044 2819 2138\",\"_mobile\":\"09445142097, 09941173095\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"ichbloodbank@gmail.com\",\"_website\":\"http:\\/\\/www.mmc.tn.gov.in\",\"_nodal_officer_\":\"Dr. Subbulakshmi\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9994512980\",\"_email_nodal_officer\":\"ichbloodbank@gmail.com\",\"_qualification_nodal_officer\":\"MD (Pathology)\",\"_category\":\"Government\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"115\\/28c\",\"_date_license_obtained\":\"31-03-1997\",\"_date_of_renewal\":\"31-12-2012\",\"_latitude\":\"13.072798\",\"_longitude\":\"80.258257\"},{\"sr_no\":\"1946\",\"_blood_bank_name\":\"Government Kilpauk Medical College Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Government Kilpauk Medical College and Hospital, 822, Poonamallee High Rd, Kilpauk, Chennai \",\"pincode\":\"600010\",\"_contact_no\":\"044 28364955\",\"_mobile\":\"9941242840\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"kmchbloodbank@gmail.com\",\"_website\":\"http:\\/\\/www.gkmc.in\\/\",\"_nodal_officer_\":\"Dr. Ravindran\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9941242840\",\"_email_nodal_officer\":\"kmchbloodbank@gmail.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Government\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"116\\/28c\",\"_date_license_obtained\":\"N\\/A\",\"_date_of_renewal\":\"31-12-2012\",\"_latitude\":\"13.078315\",\"_longitude\":\"80.243824\"},{\"sr_no\":\"1947\",\"_blood_bank_name\":\"Southern Railway Headquarters Hospital\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Ayanavaram\\r\\n2364,Chennai, Tamil Nadu \",\"pincode\":\"600023\",\"_contact_no\":\"044 2674 1624\",\"_mobile\":\"9841896599\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"N\\/A\",\"_website\":\"http:\\/\\/www.sr.indianrailways.gov.in\",\"_nodal_officer_\":\"Dr. Jaisri\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9003160548\",\"_email_nodal_officer\":\"jaisri12@yahoo.com\",\"_qualification_nodal_officer\":\"MBBS, MD, DCP, DNB\",\"_category\":\"Government\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"13.101967\",\"_longitude\":\"80.232996\"},{\"sr_no\":\"1948\",\"_blood_bank_name\":\"ESIC Hospital And Occupational Diseases Centre Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Ashok Pillar Main Road, K.K. Nagar,Chennai\",\"pincode\":\"600078\",\"_contact_no\":\"044 24893714 \",\"_mobile\":\"9840362852\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"\",\"_website\":\"\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Government\",\"_blood_component_available\":\"\",\"_apheresis\":\"\",\"_service_time\":\"24x7\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"13.034844\",\"_longitude\":\"80.2083\"},{\"sr_no\":\"1949\",\"_blood_bank_name\":\"Apollo Hospital Enterprises Limited Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"No:21, Greams Lane, Off Greams Road, Thousand Lights West, Thousand Lights, Chennai \",\"pincode\":\"600006\",\"_contact_no\":\"044 28294870, 044 2829 0200\",\"_mobile\":\"9894769935\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"drrema_m@apollohospital.co\",\"_website\":\"http:\\/\\/www.apollohospitals.com\",\"_nodal_officer_\":\"Dr. Rema Menon\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9894769935\",\"_email_nodal_officer\":\"drrema_m@apollohospital.co\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24x7\",\"_license_\":\"339\\/28c\",\"_date_license_obtained\":\"30-09-1994\",\"_date_of_renewal\":\"29-09-2017\",\"_latitude\":\"13.063084\",\"_longitude\":\"80.251472\"},{\"sr_no\":\"1950\",\"_blood_bank_name\":\"Jeevan Blood Bank and Research Centre\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"22\\/11, Wheatcrofts Road, Nungambakkam\\r\\n2368,Chennai, Tamil Nadu 600034\",\"pincode\":\"600034\",\"_contact_no\":\"0435 20220 044 2826 3113\",\"_mobile\":\"093817 75666\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"safeblood@jeevan.org\",\"_website\":\"http:\\/\\/www.jeevan.org\",\"_nodal_officer_\":\"Dr. Prabhakaran\",\"_contact_nodal_officer\":\"0435 20220 044 2826 3113\",\"_mobile_nodal_officer\":\"8939995678\",\"_email_nodal_officer\":\"safeblood@jeevan.org\",\"_qualification_nodal_officer\":\"MBBS, MD, Transfusion Medicine\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"370\\/28c\",\"_date_license_obtained\":\"12\\/9\\/1995\",\"_date_of_renewal\":\"5\\/2\\/2020\",\"_latitude\":\"13.064595\",\"_longitude\":\"80.241688\"},{\"sr_no\":\"1951\",\"_blood_bank_name\":\"Apollo Speciality Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"4th Floor, Geo Tower, Old No.319, New No.465, Anna Salai, Nandanam, Chennai\",\"pincode\":\"600035\",\"_contact_no\":\"044 24347288, 044 24331741, 044 24336119\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"drrema_m@apollohospital.co\",\"_website\":\"http:\\/\\/www.apollohospitals.com\",\"_nodal_officer_\":\"Dr. Rema Menon\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9840352531\",\"_email_nodal_officer\":\"drrema_m@apollohospital.co\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24X7\",\"_license_\":\"339\",\"_date_license_obtained\":\"30-09-1994\",\"_date_of_renewal\":\"29-09-2017\",\"_latitude\":\"13.033595\",\"_longitude\":\"80.24507\"},{\"sr_no\":\"1952\",\"_blood_bank_name\":\"Madras Medical Mission(Institute of Cardio Vascular Diseses)\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"No. 4 Dr.Jayalalitha Nagar, Mogapair\",\"pincode\":\"600050\",\"_contact_no\":\"044 26561801\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"044 26565510\",\"_email\":\"bloodbank @mmm.org.in\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Jeyaraju\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9841011418\",\"_email_nodal_officer\":\"jjayaraju@mmm.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"69\\/28c\",\"_date_license_obtained\":\"N\\/A\",\"_date_of_renewal\":\"21-12-2016\",\"_latitude\":\"13.085812\",\"_longitude\":\"80.187029\"},{\"sr_no\":\"1953\",\"_blood_bank_name\":\"Kanchi Kamakoti Child Trust Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"12 A, Nageshwara Road, Nungambakkam, Chennai\",\"pincode\":\"600034\",\"_contact_no\":\"044 42001800, Extn 205\",\"_mobile\":\"98404598978\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"\",\"_website\":\"http:\\/\\/www.kkcth.org\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Charity\",\"_blood_component_available\":\"\",\"_apheresis\":\"\",\"_service_time\":\"24x7\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"13.057336\",\"_longitude\":\"80.245785\"},{\"sr_no\":\"1954\",\"_blood_bank_name\":\"Vijaya Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"New No: 434 (OId No: 180), N.S.K Salai, Vadapalani\",\"pincode\":\"600026\",\"_contact_no\":\"044 24881392, 044 24842931, 044 24802221, 044 24802165,\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"receptionpro2009@gmail.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. K. Prabahakar\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9884015766\",\"_email_nodal_officer\":\"receptionpro2009@gmail.com\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"151\\/28c\",\"_date_license_obtained\":\"29-06-1998\",\"_date_of_renewal\":\"31-12-2016\",\"_latitude\":\"13.049867\",\"_longitude\":\"80.208392\"},{\"sr_no\":\"1955\",\"_blood_bank_name\":\"MIOT Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"No. 4\\/112 Mount Poonamelle Road, Manapakkam, Chennai\",\"pincode\":\"600089\",\"_contact_no\":\"044 22492288, 044 42002288\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"bloodbank@miothospitals.com\",\"_website\":\"http:\\/\\/www.MIOThospital.com\",\"_nodal_officer_\":\"Dr. Selvaraj\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9841067775\",\"_email_nodal_officer\":\"bloodbank@miothospitals.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24X7\",\"_license_\":\"375\\/28c\",\"_date_license_obtained\":\"23-06-2015\",\"_date_of_renewal\":\"31-12-2020\",\"_latitude\":\"13.021971\",\"_longitude\":\"80.186066\"},{\"sr_no\":\"1956\",\"_blood_bank_name\":\"Hindu Mission Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"103, GST Road, Tambram, Patel Nagar, Tambaram,Chennai\",\"pincode\":\"600045\",\"_contact_no\":\"044 22262244, Extn 193 \",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"bloodbank@hindumissionhospital.org\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Narayanamoorthy\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9677941661\",\"_email_nodal_officer\":\"bloodbank@hindumissionhospital.org\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Charity\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"169\",\"_date_license_obtained\":\"10\\/11\\/1999\",\"_date_of_renewal\":\"Sep-17\",\"_latitude\":\"12.923848\",\"_longitude\":\"80.113982\"},{\"sr_no\":\"1957\",\"_blood_bank_name\":\"Lions Blood Bank and Research Centre\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Doctors Plaza No. 5A\\/1 V.O.C. Street Opposite to Shanmugan Road, West Tambram\",\"pincode\":\"600045\",\"_contact_no\":\"044 2261474\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"\",\"_website\":\"\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Charity\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"N\\/A\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"0\",\"_longitude\":\"0\"},{\"sr_no\":\"1958\",\"_blood_bank_name\":\"International Centre for Cardio Thoracic and Vascular Diseases\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"R-30c, Ambattur Industrial Estate Road, Mogappair,Chennai\",\"pincode\":\"600101\",\"_contact_no\":\"044 42017575, 044 26564224 \",\"_mobile\":\"9790840976\",\"_helpline\":\"N\\/A\",\"_fax\":\"044 26565150\",\"_email\":\"translab@frontierlifeline.com\",\"_website\":\"http:\\/\\/www.frontierlifeline.com\",\"_nodal_officer_\":\"Dr. R. Sai Babu\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9840297408\",\"_email_nodal_officer\":\"translab@frontierlifeline.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"242\\/28c\",\"_date_license_obtained\":\"31-05-2004\",\"_date_of_renewal\":\"30-05-2019\",\"_latitude\":\"13.076019\",\"_longitude\":\"80.181451\"},{\"sr_no\":\"1959\",\"_blood_bank_name\":\"Dr.Kamatchi Memorial and Blood Component Research Centre \",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Run by Kamatchi Memorial Trust, 1 Radial Road, Pallikaranai, Rose Avenue, Chennai\",\"pincode\":\"600100\",\"_contact_no\":\"044 66300300 \",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"bloodbank@drkmh.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Priyanka\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"8754635906\",\"_email_nodal_officer\":\"bloodbank@drkmh.com\",\"_qualification_nodal_officer\":\"MD (Transfusion Medicine)\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24x7\",\"_license_\":\"269\\/28c\",\"_date_license_obtained\":\"13-03-2006\",\"_date_of_renewal\":\"12\\/3\\/2016\",\"_latitude\":\"12.949232\",\"_longitude\":\"80.209635\"},{\"sr_no\":\"1960\",\"_blood_bank_name\":\"Bharathiraja Speciality Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"20 Gopathi Narayanaswami Chetty Road, Thiyagaraya Nagar\\r\\n2379,Parthasarathy Puram, T.Nagar Chennai\",\"pincode\":\"600017\",\"_contact_no\":\"044 3011 3011\",\"_mobile\":\"9443478202\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"\",\"_website\":\"http:\\/\\/www.brhospital.in\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Private\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"13.048082\",\"_longitude\":\"80.245055\"},{\"sr_no\":\"1961\",\"_blood_bank_name\":\"Global Hospitals Blood Bank (A unit of Rabindranath GE Medical Association Pvt Ltd\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"439, Cheran Nagar, Perumbakkam\\r\\n2381,Cheran Nagar, Perumbakkam,\\r\\n2382,Chennai\",\"pincode\":\"600100\",\"_contact_no\":\"044 22777000\",\"_mobile\":\"N\\/A\",\"_helpline\":\"44777199\",\"_fax\":\"4444777100\",\"_email\":\"deepti.vij@gmail.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Deepti Sachan\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9940680832\",\"_email_nodal_officer\":\"deepti.vij@gmail.com\",\"_qualification_nodal_officer\":\"MD, Transfusion Medicine\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24x7\",\"_license_\":\"294\\/28c\",\"_date_license_obtained\":\"5\\/12\\/2008\",\"_date_of_renewal\":\"4\\/12\\/2018\",\"_latitude\":\"12.898506\",\"_longitude\":\"80.206289\"},{\"sr_no\":\"1962\",\"_blood_bank_name\":\"Sri Ramachandra Medical College and Research Institute Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"No.1, Ramachandra Nagar, Porur, Chennai\",\"pincode\":\"600116\",\"_contact_no\":\"044 24768027, 044 24768031, 044 24768033, 044 45928500\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"srmcbb@yahoo.com\",\"_website\":\"http:\\/\\/www.sriramachandra.com\",\"_nodal_officer_\":\"Dr. Vinod Kumar Panicker\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9841029877\",\"_email_nodal_officer\":\"srmcbb@yahoo.com\",\"_qualification_nodal_officer\":\"MD (Pathology)\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24x7\",\"_license_\":\"304\\/28c\",\"_date_license_obtained\":\"9\\/6\\/1984\",\"_date_of_renewal\":\"4\\/3\\/2016\",\"_latitude\":\"13.038833\",\"_longitude\":\"80.143534\"},{\"sr_no\":\"1963\",\"_blood_bank_name\":\"Rotary Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Medavakkam Main Rd, Vanuvampet, Madipakkam, Chennai\",\"pincode\":\"600091\",\"_contact_no\":\"044 43584482 044 2242 2639\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"rotarybloodbank_ngl@yahoo.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Thiyagarajan\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9841041182\",\"_email_nodal_officer\":\"rotarybloodbank_ngl@yahoo.com\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Charity\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"306\\/28c\",\"_date_license_obtained\":\"15-10-2009\",\"_date_of_renewal\":\"14-10-2009\",\"_latitude\":\"12.98107\",\"_longitude\":\"80.195475\"},{\"sr_no\":\"1964\",\"_blood_bank_name\":\"St Thomas Blood Bank and Research Centre St Thomas Hospital\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"5\\/105, II floor Defence Colony Road, St. Thomas Mount\",\"pincode\":\"16\",\"_contact_no\":\"044 22319393\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"stthomasbloodbankcrc@gmail.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Ashok\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9840974740\",\"_email_nodal_officer\":\"stthomasbloodbankcrc@gmail.com\",\"_qualification_nodal_officer\":\"MBBS, MD(TM)\",\"_category\":\"Charity\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"328\\/28c\",\"_date_license_obtained\":\"N\\/A\",\"_date_of_renewal\":\"Jul-16\",\"_latitude\":\"13.012134\",\"_longitude\":\"80.19625\"},{\"sr_no\":\"1965\",\"_blood_bank_name\":\"Right Hospitals Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"No.1, Professor Subramaniam Street, Kilpauk\",\"pincode\":\"600010\",\"_contact_no\":\"044 26403939\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"044 26430999\",\"_email\":\"rightbloodbank@gmail.com\",\"_website\":\"http:\\/\\/www.rightbloodbank@gmail.com\",\"_nodal_officer_\":\"Dr. Varadharajan\",\"_contact_nodal_officer\":\"044 26423940\",\"_mobile_nodal_officer\":\"N\\/A\",\"_email_nodal_officer\":\"rightbloodbank@gmail.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24X7\",\"_license_\":\"343\\/28c\",\"_date_license_obtained\":\"28-01-2013\",\"_date_of_renewal\":\"27-01-2018\",\"_latitude\":\"13.078914\",\"_longitude\":\"80.240581\"},{\"sr_no\":\"1966\",\"_blood_bank_name\":\"Apollo Hospitals Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Run by Apollo Hospitals Educational Trust, No. 64, Fourth Floor, Vanagaram to Ambattur Main Road, Ayanambakkam\",\"pincode\":\"600095\",\"_contact_no\":\"044 26533429\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"bloodbank_aybkm@apollohospitals.com\",\"_website\":\"http:\\/\\/www.chennai.apollohospitals.com\",\"_nodal_officer_\":\"Dr. Anila Mathew\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9840038231\",\"_email_nodal_officer\":\"bloodbank_aybkm@apollohospitals.com\",\"_qualification_nodal_officer\":\"MBBS, MD(TM)\",\"_category\":\"Charity\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"350\\/28c\",\"_date_license_obtained\":\"3\\/2\\/2014\",\"_date_of_renewal\":\"2\\/2\\/2019\",\"_latitude\":\"13.071266\",\"_longitude\":\"80.150603\"},{\"sr_no\":\"1967\",\"_blood_bank_name\":\"Landsteiner Lakshmi Memorial Research Foundation\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"14\\/90, Ground Floor, First Floor and Second Floor, Ayyavoo Street, Aminijikarai\",\"pincode\":\"600029\",\"_contact_no\":\"044 42320000, 044 26644111\",\"_mobile\":\"8012999900\",\"_helpline\":\"08925500000, 09976000000\",\"_fax\":\"N\\/A\",\"_email\":\"llmrf@yahoo.com \\/ landsteinercharities@gmail.com\",\"_website\":\"http:\\/\\/www.llmrfbloodbank.com\",\"_nodal_officer_\":\"Dr.Jeyakrishnan\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9382828101\",\"_email_nodal_officer\":\"llmrf@yahoo.com\\/landsteinercharities@gmail.com\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Charity\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"367\\/28c\",\"_date_license_obtained\":\"19-06-1995\",\"_date_of_renewal\":\"11\\/12\\/2019\",\"_latitude\":\"13.079435\",\"_longitude\":\"80.257308\"},{\"sr_no\":\"1968\",\"_blood_bank_name\":\"Madras Egmore Lions Blood Bank and Research Foundation\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Egmore Lions Blood Bank and Research Foundation, 130 Marshall Road, Egmore\\r\\n2390,Chennai\",\"pincode\":\"600008\",\"_contact_no\":\"044 28414949, 044 28414959, 044 28415959\",\"_mobile\":\"8012999900\",\"_helpline\":\"1910\",\"_fax\":\"N\\/A\",\"_email\":\"ceo@lionsbloodbank.net\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Gunalakshmi\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9941917515\",\"_email_nodal_officer\":\"ceo@lionsbloodbank.net\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Charity\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"2\\/28c\",\"_date_license_obtained\":\"25-10-1999\",\"_date_of_renewal\":\"31-12-2015\",\"_latitude\":\"13.069705\",\"_longitude\":\"80.260797\"},{\"sr_no\":\"1969\",\"_blood_bank_name\":\"Government Royapettah Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"No 1, Westcott Road, Royapettah, Chennai, Tamil Nadu \",\"pincode\":\"600014\",\"_contact_no\":\"044 28482611, 044 28483053\",\"_mobile\":\"9940030580\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"bloodbankgrh@gmail.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Duraisamy\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9444213143\",\"_email_nodal_officer\":\"bloodbankgrh@gmail.com\",\"_qualification_nodal_officer\":\"MD (Pathology)\",\"_category\":\"Government\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"13\\/28c\",\"_date_license_obtained\":\"10\\/11\\/1994\",\"_date_of_renewal\":\"31-12-2011\",\"_latitude\":\"13.054709\",\"_longitude\":\"80.265073\"},{\"sr_no\":\"1970\",\"_blood_bank_name\":\"Government Peripheral Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Government Peripheral Hospital \\r\\n2393,M-2, Third Avenue, Anna Nagar, \\r\\n2394,(Near K-4 Police Station)\\r\\n2395,Chennai\",\"pincode\":\"600102\",\"_contact_no\":\"044 26209490\",\"_mobile\":\"9551107853\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"bbmogphan@gmail.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Josephen Helen\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9444477733\",\"_email_nodal_officer\":\"bbmogphan@gmail.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Government\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"113\\/28c\",\"_date_license_obtained\":\"31-03-1997\",\"_date_of_renewal\":\"31-12-2017\",\"_latitude\":\"13.095428\",\"_longitude\":\"80.219317\"},{\"sr_no\":\"1971\",\"_blood_bank_name\":\"Government Kasthurbai Gandhi Hospital for Women & Children\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Victoria Hostel Rd, Triplicane, Chennai, Tamil Nadu\",\"pincode\":\"600005\",\"_contact_no\":\"044 28545123, 044 28545001\",\"_mobile\":\"9444955617\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"kghbloodbank@gmail.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Uma Maheshwaran\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9444955617\",\"_email_nodal_officer\":\"kghbloodbank@gmail.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Government\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"114\\/28c\",\"_date_license_obtained\":\"N\\/A\",\"_date_of_renewal\":\"31-12-2017\",\"_latitude\":\"13.059296\",\"_longitude\":\"80.278431\"},{\"sr_no\":\"1972\",\"_blood_bank_name\":\"Institute of Obstetrics and Gynaecology Government Hospital for Women and Children\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Police Commisioners Rd, Egmore, Chennai, Tamil Nadu\",\"pincode\":\"600008\",\"_contact_no\":\"044 28194896\",\"_mobile\":\"9941823003\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"iogwch@yahoo.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. P. Jayanthi\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9150598685\",\"_email_nodal_officer\":\"iogwch@yahoo.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Government\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"117\\/28c\",\"_date_license_obtained\":\"31-03-1997\",\"_date_of_renewal\":\"31-12-2017\",\"_latitude\":\"13.073689\",\"_longitude\":\"80.259207\"},{\"sr_no\":\"1973\",\"_blood_bank_name\":\"Government RSRM Hospital\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Cemetery Road, Royapuram, Chennai, Tamil Nadu \",\"pincode\":\"600013\",\"_contact_no\":\"044 25901665 \",\"_mobile\":\"9840570494\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"rsrmbloodbank@gmail.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Krishnakumar\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9840570494\",\"_email_nodal_officer\":\"rsrmbloodbank@gmail.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Government\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"118\",\"_date_license_obtained\":\"N\\/A\",\"_date_of_renewal\":\"31-12-2017\",\"_latitude\":\"13.108993\",\"_longitude\":\"80.288403\"},{\"sr_no\":\"1974\",\"_blood_bank_name\":\"Chennai Port Trust Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"No.10 Spring Heaven Road, George Town, Chennai, Tamil Nadu\",\"pincode\":\"600001\",\"_contact_no\":\"044 25362201\",\"_mobile\":\"\",\"_helpline\":\"12777\",\"_fax\":\"\",\"_email\":\"\",\"_website\":\"http:\\/\\/www.chennaiport.gov.in\\/\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Charity\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"13.085925\",\"_longitude\":\"80.291757\"},{\"sr_no\":\"1975\",\"_blood_bank_name\":\"ESI Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"The Blood Bank Medical Officer, ESI Hospital, Ayanavaram, \\r\\n2401,Chennai\",\"pincode\":\"600023\",\"_contact_no\":\"044 26449284\",\"_mobile\":\"9443285809\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"bloodbankesihospital@gmail.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Kaliselvam\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9443285809\",\"_email_nodal_officer\":\"bloodbankesihospital@gmail.com\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Government\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"87\\/28c\",\"_date_license_obtained\":\"31-03-1997\",\"_date_of_renewal\":\"31-12-2012\",\"_latitude\":\"13.094828\",\"_longitude\":\"80.240417\"},{\"sr_no\":\"1976\",\"_blood_bank_name\":\"Rotary Central TTK Voluntary Health Services (Dr Raganathan Memorial Blood Bank)\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Tharamani, Adyar, Chennai, Tamil Nadu\",\"pincode\":\"600113\",\"_contact_no\":\"044 22541692, 044 22542829\",\"_mobile\":\"9444021560\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"vhsbb@yahoo.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Mythily\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9444021560\",\"_email_nodal_officer\":\"vhsbb@yahoo.com\",\"_qualification_nodal_officer\":\"MBBS, DCP\",\"_category\":\"Charity\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"15\\/28c\",\"_date_license_obtained\":\"1964\",\"_date_of_renewal\":\"N\\/A\",\"_latitude\":\"13.002059\",\"_longitude\":\"80.24716\"},{\"sr_no\":\"1977\",\"_blood_bank_name\":\" AA Lab Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Old No 12 New No 23, 5th Cross Street, Nungambakkam\\r\\n2404,Nungambakkam\\r\\n2405,Chennai, Tamil Nadu\",\"pincode\":\"600034\",\"_contact_no\":\"044 28170930 \",\"_mobile\":\"09444368721, 09884067846\",\"_helpline\":\"N\\/A\",\"_fax\":\" N\\/A\",\"_email\":\"\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Gandhi Mahalingam\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9884067846\",\"_email_nodal_officer\":\"nungiaalab@gmail.com\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Charity\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"43\\/28c\",\"_date_license_obtained\":\"16-03-1989\",\"_date_of_renewal\":\"31-12-2017\",\"_latitude\":\"13.054067\",\"_longitude\":\"80.239828\"},{\"sr_no\":\"1978\",\"_blood_bank_name\":\"Sree Venkateswara Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"158, Mannarswamy Kovil Street\\r\\n2407,Royapuram\\r\\n2408,Chennai, Tamil Nadu\",\"pincode\":\"600013\",\"_contact_no\":\"044 25960226 \",\"_mobile\":\"09841067775, 09786631490\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"drkselvarajan@yahoo.co.in\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Sivarajan\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9841067775\",\"_email_nodal_officer\":\"drkselvarajan@yahoo.co.in\",\"_qualification_nodal_officer\":\"MBBS, DMRT\",\"_category\":\"Private\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"48\\/28c\",\"_date_license_obtained\":\"5\\/2\\/1999\",\"_date_of_renewal\":\"2012\",\"_latitude\":\"13.109828\",\"_longitude\":\"80.281945\"},{\"sr_no\":\"1979\",\"_blood_bank_name\":\"CSI Rainy Multi Speciality Hospital\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"45, GA Road, Old Washermanpet, Chennai, Tamil Nadu \",\"pincode\":\"600021\",\"_contact_no\":\"044 40505050\",\"_mobile\":\"\",\"_helpline\":\"\",\"_fax\":\"\",\"_email\":\"\",\"_website\":\"http:\\/\\/www.csirainyhospital.com\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Private\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"13.11812\",\"_longitude\":\"80.290511\"},{\"sr_no\":\"1980\",\"_blood_bank_name\":\"Dhanvandri Blood Bank and Research Centre of Sreemahalakshmi Charitable Health Trust\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"F-1, 52A, T.Nagar, S W Boag Rd,CIT Nagar, Chennai, Tamil Nadu\",\"pincode\":\"600017\",\"_contact_no\":\"044 24310660, 044 24310610\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"siva_ramank@yahoo.co.in\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Jeyakumaresan\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9445250394\",\"_email_nodal_officer\":\"siva_ramank@yahoo.co.in\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Charity\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24x7\",\"_license_\":\"174\\/28c\",\"_date_license_obtained\":\"1-Dec\",\"_date_of_renewal\":\"16-Dec\",\"_latitude\":\"13.031013\",\"_longitude\":\"80.231629\"},{\"sr_no\":\"1981\",\"_blood_bank_name\":\"ACS General Hospital \",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Run by Dr MGR Educational Reaserch Institute, Poonamalle High Road, Chennai- Bangalore Highway NH4, Velappanchava\\r\\n2412,Chennai, Tamil Nadu \",\"pincode\":\"600077\",\"_contact_no\":\"044 23782176, 044 23782186, 044 26802133\",\"_mobile\":\"\",\"_helpline\":\"\",\"_fax\":\"\",\"_email\":\"\",\"_website\":\"http:\\/\\/www.acsmch.ac.in\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Private\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"13.06852\",\"_longitude\":\"80.131421\"},{\"sr_no\":\"1982\",\"_blood_bank_name\":\"Muthukumaran Medical College Hospital and Research Institute\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Chikkarayapuram, Chennai, Tamil Nadu\",\"pincode\":\"600069\",\"_contact_no\":\"044 66344000, 044 24784000\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"drramarao4@yahoo.com\",\"_website\":\"\",\"_nodal_officer_\":\"Dr. Ramarao\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9941252447\",\"_email_nodal_officer\":\"drramarao4@yahoo.com\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"308\\/28c\",\"_date_license_obtained\":\"2\\/2\\/2010\",\"_date_of_renewal\":\"2\\/2\\/2020\",\"_latitude\":\"13.017931\",\"_longitude\":\"80.108969\"},{\"sr_no\":\"1983\",\"_blood_bank_name\":\"Tagore Medical College Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Vandalur - Kelambakkam Road, Melakkottaiyur Post, Near Vandalur, Rathinamangalam, Chennai, Tamil Nadu\",\"pincode\":\"600127\",\"_contact_no\":\" 044 30101111\",\"_mobile\":\"9551757371, 9443704267\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"pathology@tagoremch.com\",\"_website\":\"http:\\/\\/www.tagoremch.com\",\"_nodal_officer_\":\"Dr. Ponnusamy.V\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9443704267\",\"_email_nodal_officer\":\"pathology@tagoremch.com\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"312\\/28c\",\"_date_license_obtained\":\"7\\/9\\/2010\",\"_date_of_renewal\":\"06.09.2015\",\"_latitude\":\"12.860204\",\"_longitude\":\"80.291655\"},{\"sr_no\":\"1984\",\"_blood_bank_name\":\"Fortis Malar Hospitals Limited Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"52, First Main Road, Gandhi Nagar, Adyar\",\"pincode\":\"600020\",\"_contact_no\":\"044 42892222\",\"_mobile\":\"9176633665\",\"_helpline\":\"044 42892222\",\"_fax\":\"N\\/A\",\"_email\":\"bloodbank.malar@fortishealthcare.com\",\"_website\":\"http:\\/\\/www.fortismalar.com\\/\",\"_nodal_officer_\":\"Dr. Jyothsna Codady\",\"_contact_nodal_officer\":\"044 42892222\",\"_mobile_nodal_officer\":\"9840064787\",\"_email_nodal_officer\":\"bloodbank.malar@fortishealthcare.com\",\"_qualification_nodal_officer\":\"MBBS\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"NO\",\"_service_time\":\"24X7\",\"_license_\":\"313\",\"_date_license_obtained\":\"21-10-2010\",\"_date_of_renewal\":\"20-10-2015\",\"_latitude\":\"13.01006\",\"_longitude\":\"80.258702\"},{\"sr_no\":\"1985\",\"_blood_bank_name\":\"Madha Medical College and Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"State Highway 113,Thandalam,Kovur, Harijana Colony\\r\\n2417,Chennai, Tamil Nadu\",\"pincode\":\"600128\",\"_contact_no\":\"044 24780333 \",\"_mobile\":\"8148077777\",\"_helpline\":\"\",\"_fax\":\"\",\"_email\":\"\",\"_website\":\"http:\\/\\/www.madhagroups.org\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Private\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"13.004678\",\"_longitude\":\"80.112061\"},{\"sr_no\":\"1986\",\"_blood_bank_name\":\"Billroth Hospitals Limited Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"No.56 & 57, New Door No,5& AMP:7 Gajapathy Street, Shenoy Nagar, Chennai\",\"pincode\":\"600030\",\"_contact_no\":\"044 42921767\",\"_mobile\":\"9840538108\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"bloodbank1@billrothhospital.com\",\"_website\":\"http:\\/\\/www.billrothhospitals.com\",\"_nodal_officer_\":\"Dr. K.Selvarajan\",\"_contact_nodal_officer\":\"044 42921767\",\"_mobile_nodal_officer\":\"9841067775\",\"_email_nodal_officer\":\"bloodbank@billrothhospital.com\",\"_qualification_nodal_officer\":\"MBBS.,DCP\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24x7\",\"_license_\":\"365\\/28c\",\"_date_license_obtained\":\"28-10-2014\",\"_date_of_renewal\":\"28-10-2019\",\"_latitude\":\"13.075671\",\"_longitude\":\"80.227297\"},{\"sr_no\":\"1987\",\"_blood_bank_name\":\"Government Stanley Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"305 OSH Rd, Royapuram, Chennai, Tamil Nadu\",\"pincode\":\"600001\",\"_contact_no\":\"044 25284941\",\"_mobile\":\"9840053283\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"N\\/A\",\"_website\":\"http:\\/\\/www.stanleymedicalcollege.ac.in\\/\",\"_nodal_officer_\":\"Dr. RajKumar\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9840053283\",\"_email_nodal_officer\":\"N\\/A\",\"_qualification_nodal_officer\":\"MBBS,DCP\",\"_category\":\"Government\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24X7\",\"_license_\":\"12\\/28c\",\"_date_license_obtained\":\"N\\/A\",\"_date_of_renewal\":\"N\\/A\",\"_latitude\":\"13.105854\",\"_longitude\":\"80.285439\"},{\"sr_no\":\"1988\",\"_blood_bank_name\":\"M\\/S SRM Institutes For Medical Science\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\" No.1 Block B, First Floor,\\r\\n2421,Jawaharlal Nehru Salai, ( 100 feet road) \\r\\n2422,Vadapalani\",\"pincode\":\"600026\",\"_contact_no\":\"044 23626815\",\"_mobile\":\"\",\"_helpline\":\"\",\"_fax\":\"\",\"_email\":\"transfusionmedicine@simshospitals.com\",\"_website\":\"\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Private\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"0\",\"_longitude\":\"0\"},{\"sr_no\":\"1989\",\"_blood_bank_name\":\"Government Kasturba Gandhi Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chennai\",\"_address\":\"Chennai\",\"pincode\":\"600008\",\"_contact_no\":\"\",\"_mobile\":\"\",\"_helpline\":\"\",\"_fax\":\"\",\"_email\":\"\",\"_website\":\"\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Government\",\"_blood_component_available\":\"\",\"_apheresis\":\"\",\"_service_time\":\"\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"0\",\"_longitude\":\"0\"},{\"sr_no\":\"1990\",\"_blood_bank_name\":\"Sree Balaji General Hospital Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Chrompet\",\"_address\":\"7, Works Road, Chrompet,Chennai\",\"pincode\":\"600044\",\"_contact_no\":\"044 22415600 044 2241 5603\",\"_mobile\":\"N\\/A\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"\",\"_website\":\"http:\\/\\/www.sbmch.ac.in\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Private\",\"_blood_component_available\":\"NO\",\"_apheresis\":\"NO\",\"_service_time\":\"24x7\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"12.954308\",\"_longitude\":\"80.137703\"},{\"sr_no\":\"1991\",\"_blood_bank_name\":\"Indian Red Cross Society Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Egmore\",\"_address\":\"Tamil Nadu Branch, No, 50 Montieth Road,Egmore\",\"pincode\":\"600008\",\"_contact_no\":\"044 28554425, 044 28594888,\",\"_mobile\":\"09443076418, 08754040286, 09444441898\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"\",\"_website\":\"\",\"_nodal_officer_\":\"\",\"_contact_nodal_officer\":\"\",\"_mobile_nodal_officer\":\"\",\"_email_nodal_officer\":\"\",\"_qualification_nodal_officer\":\"\",\"_category\":\"Charity\",\"_blood_component_available\":\"\",\"_apheresis\":\"\",\"_service_time\":\"N\\/A\",\"_license_\":\"\",\"_date_license_obtained\":\"\",\"_date_of_renewal\":\"\",\"_latitude\":\"0\",\"_longitude\":\"0\"},{\"sr_no\":\"1992\",\"_blood_bank_name\":\"The Tamil Nadu Dr. MGR Medical University Blood Bank\",\"_state\":\"Tamil Nadu\",\"_district\":\"CHENNAI\",\"_city\":\"Guindy\",\"_address\":\"69, Anna Salai, Guindy,Chennai\",\"pincode\":\"600032\",\"_contact_no\":\"0442 22353546 044 2235 3547\",\"_mobile\":\"\",\"_helpline\":\"N\\/A\",\"_fax\":\"N\\/A\",\"_email\":\"\",\"_website\":\"http:\\/\\/www.tnmgrmu.ac.com\",\"_nodal_officer_\":\"Dr. P. Arumugam\",\"_contact_nodal_officer\":\"N\\/A\",\"_mobile_nodal_officer\":\"9445098949\",\"_email_nodal_officer\":\"arumugham.p@tnmgrmu.ac.in\",\"_qualification_nodal_officer\":\"MBBS, MD\",\"_category\":\"Government\",\"_blood_component_available\":\"YES\",\"_apheresis\":\"YES\",\"_service_time\":\"24x7\",\"_license_\":\"191\\/28C\",\"_date_license_obtained\":\"1\\/1\\/2008\",\"_date_of_renewal\":\"31-12-2012\",\"_latitude\":\"13.009896\",\"_longitude\":\"80.218188\"}],\"version\":\"2.0.0\"}";


    private CameraPosition mCameraPosition;
    private GoogleApiClient mGoogleApiClient;
    private final LatLng mDefaultLocation = new LatLng(13.082681, 80.270715);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private Location mLastKnownLocation;
    private String TAG = "checkmap";

    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // Used for selecting the current place.
    private final int mMaxEntries = 5;
    private String[] mLikelyPlaceNames = new String[mMaxEntries];
    private String[] mLikelyPlaceAddresses = new String[mMaxEntries];
    private String[] mLikelyPlaceAttributions = new String[mMaxEntries];
    private LatLng[] mLikelyPlaceLatLngs = new LatLng[mMaxEntries];
    public Location Mylocation;
    int count;


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count==0)
                    get_default_users();
                if(count % 2 == 1){
                    for (Marker m : markerPoints) {
                        m.setVisible(true);
                    }
                    for (Marker p : dmarkerPoints) {
                        p.setVisible(false);
                    }
                }
                else{
                    for (Marker p : dmarkerPoints) {
                        p.setVisible(true);
                    }
                    for (Marker m : markerPoints) {
                        m.setVisible(false);
                    }
                }
                count++;
            }
        });

        SeekBar range = (SeekBar) findViewById(R.id.seekBar2);
        range.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //new_mark();
                //
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /*FragmentActivity*/, this /*OnConnectionFailedListener*/)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        mGoogleApiClient.connect();

    }

    public void onMapSearch(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        AutoCompleteTextView locationSearch = (AutoCompleteTextView) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;
        if (location.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please Search a valid location..", Toast.LENGTH_LONG).show();
        } else if (!isNetworkAvailable()) {
            Snackbar.make(view, "No Network  :-(", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent dn = new Intent(this,donate.class);
            startActivity(dn);
        } else if (id == R.id.nav_gallery) {

            Intent rq = new Intent(this,request.class);
            startActivity(rq);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /// RELATED TO MAPS UNDER THIS
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        updateLocationUI();
        getDeviceLocation();
        get_default_markers();
    }


    private void getDeviceLocation() {

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mLocationPermissionGranted) {
            mLastKnownLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);
            Mylocation = mLastKnownLocation;

        }

        noLocation();

        // Set the map's camera position to the current location of the device.
        if (mCameraPosition != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mLastKnownLocation != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mLastKnownLocation.getLatitude(),
                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
        } else {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        }
    }


    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        if (mLocationPermissionGranted) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            mMap.setMyLocationEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mLastKnownLocation = null;
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }


    @Override
    public void onConnected(Bundle connectionHint) {
        // Build the map.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        // Refer to the reference doc for ConnectionResult to see what error codes might
        // be returned in onConnectionFailed.
        Log.d(TAG, "Play services connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "Play services connection suspended");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }

    }


    //wheater internet is available
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /// to enable gps automatically

    private GoogleApiClient googleApiClient;

    final static int REQUEST_LOCATION = 199;


    public boolean noLocation() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //  buildAlertMessageNoGps();

            enableLoc();
            return true;
        }
        return false;

    }


    private void enableLoc() {


        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.d("loc", "on loc");
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(MainActivity.this, REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUEST_LOCATION:
                switch (resultCode) {
                    case Activity.RESULT_CANCELED: {
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(getApplicationContext(), "GPS disabled viewing offline", Toast.LENGTH_LONG).show();

                    }
                    default: {
                        break;
                    }
                }
                break;
        }

    }

    public void get_default_users(){
        dmarkerPoints.add(mMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.839, 80.136))
                .title("bbname").icon(BitmapDescriptorFactory.defaultMarker(HUE_BLUE))));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(MainActivity.this, detail.class);
                in.putExtra("serial", marker.getSnippet());
                startActivity(in);
                return true;
            }
        });
    }

    public void get_default_markers() {

        String jsonData;

        jsonData = defaultjson;

        try {

            JSONObject obj = new JSONObject(jsonData);
            JSONArray results = obj.getJSONArray("records");
            for (int i = 0; i < results.length(); i++) {
                JSONObject rec = results.getJSONObject(i);
                String bbname = rec.getString("_blood_bank_name");
                String laty = rec.getString("_latitude");
                String longy = rec.getString("_longitude");
                String ser = rec.getString("sr_no");


                double nlat = Double.parseDouble(laty);
                double nlong = Double.parseDouble(longy);
                markerPoints.add(mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(nlat, nlong))
                        .title(bbname)
                        .snippet(ser)));

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(MainActivity.this, detail.class);
                        in.putExtra("serial", marker.getSnippet());
                        startActivity(in);
                        return true;
                    }
                });
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}




    //api-key=579b464db66ec23bdd00000185565bcb28fb4e0853b95ebb66825cb0


