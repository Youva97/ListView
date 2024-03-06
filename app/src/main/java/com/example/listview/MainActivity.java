package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private StringBuffer buffer;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);



        MaDataBase SQLdb = new MaDataBase(this);

        /*
        SQLdb.insertionPRODUCTS("Battle War", "Plongez dans l'effervescence de la bataille où seuls les plus forts survivent. Affrontez des vétérans de la guerre dans des combats épiques pour la suprématie ultime.", String.valueOf(R.drawable.image10));
        SQLdb.insertionPRODUCTS("Arcadia", "Explorez un monde fantastique rempli de mystères et de dangers dans ce MMORPG conçu pour les puristes. Partez à l'aventure, développez vos compétences et forgez votre destinée dans ce royaume immersif.", String.valueOf(R.drawable.image9));
        SQLdb.insertionPRODUCTS("Talion", "Plongez dans l'univers médiéval fantastique où les chevaliers combattent les dragons et les sorciers lancent des sorts puissants. Incarnez un héros légendaire et explorez des terres mystérieuses remplies de danger et d'aventure.", String.valueOf(R.drawable.images8));
        SQLdb.insertionPRODUCTS("Galactic Conquest", "Prenez le commandement de votre propre flotte spatiale et menez des batailles épiques pour la domination de la galaxie. Explorez des mondes inconnus, rencontrez des races extraterrestres et affirmez votre puissance dans ce jeu de stratégie interstellaire.", String.valueOf(R.drawable.image7));
        SQLdb.insertionPRODUCTS("Cybernetic Revolution", "Plongez dans un futur dystopique où la technologie a bouleversé l'ordre établi. Affrontez des corporations puissantes, piratage des réseaux informatiques et combattez pour la liberté dans ce jeu de rôle cyberpunk passionnant.", String.valueOf(R.drawable.image6));
        SQLdb.insertionPRODUCTS("Space Explorers", "Explorez les confins de l'univers dans ce jeu de simulation spatiale passionnant. Construisez votre propre vaisseau spatial, découvrez de nouvelles planètes et établissez des colonies dans des mondes lointains alors que vous recherchez de nouvelles frontières.", String.valueOf(R.drawable.image5));
        SQLdb.insertionPRODUCTS("Legendary Champions", "Rejoignez l'arène et affrontez les plus grands champions du monde dans des combats épiques pour la gloire et la fortune. Incarnez des héros légendaires, maîtrisez des compétences uniques et prouvez votre valeur dans ce jeu de combat ultime.", String.valueOf(R.drawable.image4));
        SQLdb.insertionPRODUCTS("Underworld Odyssey", "Plongez dans les profondeurs insondables d'un monde souterrain rempli de danger et d'aventure. Affrontez des créatures terrifiantes, résolvez des énigmes complexes et découvrez d'anciens secrets dans ce jeu d'exploration captivant.", String.valueOf(R.drawable.image3));
        SQLdb.insertionPRODUCTS("Legends of Valor", "Devenez une légende dans ce jeu d'action-RPG palpitant. Affrontez des monstres redoutables, explorez des donjons mystérieux et découvrez des trésors fabuleux alors que vous progressez dans une quête épique pour sauver le royaume.", String.valueOf(R.drawable.image2));
        SQLdb.insertionPRODUCTS("Fantasy Chronicles", "Explorez un monde magique rempli de créatures fantastiques et de mystères anciens. Incarnez un héros courageux, accomplissez des quêtes épiques et devenez une légende dans ce MMORPG immersif.", String.valueOf(R.drawable.image1));
        */


        Cursor cursor = SQLdb.lireTable();

        if (cursor.getCount() == 0){
            Toast.makeText(MainActivity.this, "Aucune correspondance trouvée.", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<HashMap<String,String>> listItem = new ArrayList<HashMap<String,String>>();

            HashMap<String,String> map;

            map = new HashMap<String,String>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                map = new HashMap<String,String>();
                map.put("idProduct" , String.valueOf(cursor.getInt(0)));
                map.put("TITLE" , (cursor.getString(1)) );
                map.put("DESCRIPTION" ,(cursor.getString(2)));
                map.put("IMAGE", (cursor.getString(3)) );
                cursor.moveToNext();
                listItem.add(map);

            }
            SimpleAdapter monAdapter = new SimpleAdapter(this.getBaseContext(),
                    listItem, R.layout.affichage_item, new String[] {"IMAGE", "TITLE", "DESCRIPTION"}, new int[] {R.id.imageView,R.id.txtTitre,R.id.txtDescription});

            listView.setAdapter(monAdapter);
            cursor.close();


        }


    }
}