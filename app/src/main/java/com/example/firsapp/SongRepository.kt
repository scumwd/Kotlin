package com.example.firsapp

object SongRepository {
    var songList:List<Song> = getSong()

    fun getSong():List<Song> {
        return arrayListOf(
            Song(R.drawable.drums,"Money",R.raw.drums,"The Drums"),
            Song(R.drawable.gorilaz,"Плыли мы по морю",R.raw.gorillaz,"Gorillaz x Бомж x Гачи"),
            Song(R.drawable.leen,"Red Bottom Sky",R.raw.lean,"Yung Lean"),
            Song(R.drawable.nikola,"Lippo Pippo",R.raw.nikola,"Nikola Chen"),
            Song(R.drawable.ploho,"Страна дураков",R.raw.ploho,"Ploho"),
            Song(R.drawable.room,"Little Room",R.raw.room,"The White Stripes"),
            Song(R.drawable.sister,"Высота",R.raw.sister,"Сёстры x Скриптонит")
        )
    }

    fun getPosition(author:String,title:String):Int{
        for(i in 1 until songList.size){
            if(songList[i].title == title && songList[i].author == author){
                return i
            }
        }
        return 0
    }
}