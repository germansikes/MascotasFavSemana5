package com.dreamsense.MascotasFavsemana5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.dreamsense.MascotasFavsemana5.adaptador.MascotaAdaptador;
import com.dreamsense.MascotasFavsemana5.adaptador.PageAdapter;
import com.dreamsense.MascotasFavsemana5.fragments.FragmentPerfil;
import com.dreamsense.MascotasFavsemana5.fragments.FragmentRecycler;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

/**
 * Created by Gabriel on 06/07/2016.
 */
public class ListaMascotas extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public MascotaAdaptador adaptador;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private Toolbar toolbar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_mascotas);

        toolbar = (Toolbar) findViewById(R.id.toolbar);


        toolbar2 = (Toolbar) findViewById(R.id.toolbar1);
        //setSupportActionBar(toolbar2);

//        ActionBar ab = getSupportActionBar();
//        ab.setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setLogo(R.drawable.ic_footprint);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ListaMascotas Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.dreamsense.courseramascotas/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "ListaMascotas Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.dreamsense.courseramascotas/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    private ArrayList<Fragment> agregarFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentRecycler());
        fragments.add(new FragmentPerfil());
        return fragments;
    }


    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_dog_house);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_front);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.resources, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.action_fav:
                //Al presionar el botón de favoritos se muestra el nuevo activity con los favs seleccionados
                if(MascotaAdaptador.mascotasFavs.size() > 0) {
                    intent = new Intent(ListaMascotas.this, MascotasFavoritos.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(ListaMascotas.this, "No es posible acceder a esta ventana, no tienes favoritos.", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.mContacto:
                //Al activity para información de contacto
                intent = new Intent(ListaMascotas.this, FormularioContacto.class);
                startActivity(intent);
                break;

            case R.id.mAcercaDe:
                //Al activity para la BIO del contacto.
                intent = new Intent(ListaMascotas.this, BioContacto.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
