package Java.CombineCSV;

import Java.HandleCSV.CompleteStoreCSV;
import Java.HandleCSV.Sort;

public class CombineCSV {
	public static String[][] combineCSV() {
		String[][] spotifyAndYoutubeData = CompleteStoreCSV.completeSpotifyAndYoutubeCSV();
		String[][] topSpotifySongsData = CompleteStoreCSV.completeTopSpotifySongs();
		Sort.sort(spotifyAndYoutubeData);
		Sort.sort(topSpotifySongsData);
		
		// Columns
			//From Spotify and Youtube
				// 0: key (albumname danceability energy)
				// 1: Column number
				// 2: Artist
				// 3: Url_spotify
				// 4: Track
				// 5: Album
				// 6: Album_type
				// 7: Uri
				// 8: Danceability
				// 9: Energy
				// 10: Key 
				// 11: Loudness
				// 12: Speechiness
				// 13: Acousticness
				// 14: Instrumentalness
				// 15: Liveness
				// 16: Valence
				// 17: Tempo
				// 18: Duration_ms
				// 19{ Url_youtube
				// 20: Title
				// 21: Channel
				// 22: Views
				// 23: Likes
				// 24: Comments
				// 25: Description
				// 26: Licensed
				// 27: official_vide
				// 28: Stream
			// Top Spotify Songs
				// 29: spotify_id
				// 30: name
				// 31: arists
				// 32: daily_rank
				// 33: daily_movement
				// 34: weekly_movement
				// 35: country
				// 36: snapshot_date
				// 37: popularity
				// 38: is_explicit
				// 39: duration_ms
				// 40: album_name
				// 41: album_release_date
				// 42: danceability
				// 43: energy
				// 44: key
				// 45: loudness
				// 46: mode
				// 47: speechiness
				// 48: acousticness
				// 49: instrumentalness
				// 50: liveness
				// 51: valence
				// 52: tempo
				// 53: time_signature
		
		String[][] songData = new String[spotifyAndYoutubeData.length + topSpotifySongsData.length][54];
		int spotifyAndYoutubeDataIndex = 0;
		int topSpotifySongsDataIndex = 0;
		for (int i = 0; i < songData.length; i++) {
			if (spotifyAndYoutubeDataIndex > spotifyAndYoutubeData.length - 1 && topSpotifySongsDataIndex > topSpotifySongsData.length - 1)
				break;
			if (spotifyAndYoutubeDataIndex > spotifyAndYoutubeData.length - 1) {
				songData[i][0] = topSpotifySongsData[topSpotifySongsDataIndex][topSpotifySongsData[0].length - 1];
				for (int j = 1; j < 25; j++)
					songData[i][j + 29] = topSpotifySongsData[topSpotifySongsDataIndex][j];
				topSpotifySongsDataIndex++;
				continue;
			}
			if (topSpotifySongsDataIndex > topSpotifySongsData.length - 1) {
				songData[i][0] = spotifyAndYoutubeData[spotifyAndYoutubeDataIndex][spotifyAndYoutubeData[0].length - 1];
				for (int j = 1; j < 29; j++)
					songData[i][j] = spotifyAndYoutubeData[spotifyAndYoutubeDataIndex][j];
				spotifyAndYoutubeDataIndex++;
				continue;
			}
			if (spotifyAndYoutubeData[spotifyAndYoutubeDataIndex][spotifyAndYoutubeData[0].length - 1].compareTo(topSpotifySongsData[topSpotifySongsDataIndex][topSpotifySongsData[0].length - 1]) > 0) {
				songData[i][0] = topSpotifySongsData[topSpotifySongsDataIndex][topSpotifySongsData[0].length - 1];
				for (int j = 1; j < 25; j++)
					songData[i][j + 29] = topSpotifySongsData[topSpotifySongsDataIndex][j];
				topSpotifySongsDataIndex++;
				continue;
			}
			if (spotifyAndYoutubeData[spotifyAndYoutubeDataIndex][spotifyAndYoutubeData[0].length - 1].compareTo(topSpotifySongsData[topSpotifySongsDataIndex][topSpotifySongsData[0].length - 1]) == 0) {
				for (int j = 1; j < 25; j++)
					songData[i][j + 29] = topSpotifySongsData[topSpotifySongsDataIndex][j];
				topSpotifySongsDataIndex++;
				songData[i][0] = spotifyAndYoutubeData[spotifyAndYoutubeDataIndex][spotifyAndYoutubeData[0].length - 1];
				for (int j = 1; j < 29; j++)
					songData[i][j] = spotifyAndYoutubeData[spotifyAndYoutubeDataIndex][j];
				spotifyAndYoutubeDataIndex++;
				continue;
			}
			if (spotifyAndYoutubeData[spotifyAndYoutubeDataIndex][spotifyAndYoutubeData[0].length - 1].compareTo(topSpotifySongsData[topSpotifySongsDataIndex][topSpotifySongsData[0].length - 1]) < 0) {
				songData[i][0] = spotifyAndYoutubeData[spotifyAndYoutubeDataIndex][spotifyAndYoutubeData[0].length - 1];
				for (int j = 1; j < 29; j++)
					songData[i][j] = spotifyAndYoutubeData[spotifyAndYoutubeDataIndex][j];
				spotifyAndYoutubeDataIndex++;
				continue;
			}
		}
		return songData;
	}
}
