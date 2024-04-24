function communicateServer(url, type) {
  const outputUrl = fetch(
    //`https://www.omdbapi.com/?s=${ele_txtSearchInput.value}&apikey=36e789c2`
    url
  )
    .then((val) => {
      return val.json();
    })
    .then((json) => {
      //console.log(json);
      if (type === "parent") {
        const eventGenerateCards = new CustomEvent("refreshCards", {
          detail: { response: json },
        });
        window.dispatchEvent(eventGenerateCards);
      } else {
        const eventGenerateCards = new CustomEvent("child", {
          detail: { response: json },
        });
        window.dispatchEvent(eventGenerateCards);
      }
    })
    .catch((err) => {
      console.log("Error while fetching movies..." + err);
    });
}

const ele_txtSearchInput = document.getElementById("txtSearchInput");
ele_txtSearchInput.addEventListener("keyup", () => {
  console.log(ele_txtSearchInput.value);
  if (ele_txtSearchInput.value.length > 3) {
    communicateServer(
      `https://www.omdbapi.com/?s=${ele_txtSearchInput.value}&apikey=36e789c2`,
      "parent"
    );
  }
});

function refreshCards() {
  const elemovieTiles = document.getElementById("movieTiles");
  while (elemovieTiles.hasChildNodes()) {
    elemovieTiles.removeChild(elemovieTiles.firstChild);
  }

  window.addEventListener("refreshCards", (event) => {
    const ele_movie_tile_card = document.createElement("div");
    ele_movie_tile_card.classList.add("movie-tile-card");
    const jsonResp = event.detail.response;
    jsonResp.Search.forEach((item) => {
      //   <div class="movie-tile-card">

      // 	<i class="fa-solid fa-bookmark"></i>
      const ele_fa_bookmark = document.createElement("i");
      ele_fa_bookmark.classList.add("fa-solid", "fa-bookmark");
      ele_movie_tile_card.appendChild(ele_fa_bookmark);
      // 	<div id="thumbnail">
      const ele_thumbnail = document.createElement("div");
      ele_thumbnail.id = "thumbnail";

      // 		<img src="https://m.media-amazon.com/images/M/MV5BYzA4ZjVhYzctZmI0NC00ZmIxLWFmYTgtOGIxMDYxODhmMGQ2XkEyXkFqcGdeQXVyNjExODE1MDc@._V1_SX300.jpg">
      const ele_img_thumb = document.createElement("img");
      ele_img_thumb.src = item.Poster;

      // 	</div>
      ele_thumbnail.appendChild(ele_img_thumb);

      ele_movie_tile_card.appendChild(ele_thumbnail);
      // 	<div id="rating-sec">
      const ele_rating_sec = document.createElement("div");
      ele_rating_sec.id = "rating-sec";
      // 		<i class="fa-solid fa-star"></i>
      // 		<h3>3.9</h3>
      const ele_fa_star_solid = document.createElement("i");
      ele_fa_star_solid.classList.add("fa-solid", "fa-star");

      const ele_rating_h3 = document.createElement("h3");
      communicateServer(
        `http://www.omdbapi.com/?i=${item.imdbID}&apikey=36e789c2`,
        "child"
      );

      window.addEventListener("child", (event) => {
        ele_rating_h3.textContent = event.detail.response.imdbRating;
      });
      ele_movie_tile_card.appendChild(ele_rating_sec);
      const ele_fa_star_regular = document.createElement("i");
      ele_fa_star_regular.classList.add("fa-regular", "fa-star");
      // 		<i class="fa-regular fa-star"></i>

      ele_rating_sec.appendChild(ele_fa_star_solid);
      ele_rating_sec.appendChild(ele_rating_h3);
      ele_rating_sec.appendChild(ele_fa_star_regular);
      // 	</div>

      // 	<div id="movie-title">1. Fallout</div>
      const ele_movie_title = document.createElement("div");
      ele_movie_title.id = "movie-title";

      ele_movie_tile_card.appendChild(ele_movie_title);

      // 	<div id="watch-options">
      const ele_watch_option = document.createElement("div");
      ele_watch_option.id = "watch-options";

      // 		<button>Watch Options</button>

      const buttonWatchOpt = document.createElement("button");
      buttonWatchOpt.textContent = "Watch Options";
      // 	</div>
      ele_watch_option.appendChild(buttonWatchOpt);

      ele_movie_tile_card.appendChild(ele_watch_option);
      // 	<div class="movie-tile-card-footer"></div>
      // </div>
      //console.log(item);
    });
    console.log(event.detail.response);
    elemovieTiles.appendChild(ele_movie_tile_card);
  });
}

refreshCards();
