/*
 * Copyright (c) 2020 Hemanth Savarla.
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 */
package code.name.monkey.retromusic.helper

import code.name.monkey.retromusic.model.Song
import code.name.monkey.retromusic.service.MusicService

object ShuffleHelper {

    fun makeShuffleList(listToShuffle: MutableList<Song>, current: Int, shuffleMode:Int) {
        if (listToShuffle.isEmpty()) return
        if (current >= 0) {
            val song = listToShuffle.removeAt(current)
            when(shuffleMode) {
                MusicService.SHUFFLE_MODE_ALBUM -> shuffleAlbums(listToShuffle);
                else -> listToShuffle.shuffle()
            }
            listToShuffle.add(0, song)
        } else {
            when(shuffleMode) {
                MusicService.SHUFFLE_MODE_ALBUM -> shuffleAlbums(listToShuffle);
                else -> listToShuffle.shuffle()
            }
        }
    }
    private fun shuffleAlbums(listToShuffle: MutableList<Song>){
        val albums = listToShuffle.groupBy { it.albumId }
        val shuffledAlbums = albums.keys.shuffled()
        listToShuffle.clear()
        shuffledAlbums.forEach { listToShuffle.addAll(albums[it] ?: emptyList()) }
    }
}
