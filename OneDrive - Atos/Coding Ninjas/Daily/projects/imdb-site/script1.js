const BASE_URL = "https://www.omdbapi.com/";
const MOVIE_TITLE_PARAM = "s";
const IMDB_ID_PARAM = "i";
const API_KEY = "36e789c2";
var movieDetstatus = false;

let wishList = [];

const EVENT_AND_CLASS_BINDING = [
  {
    searchParam: "s",
    eventName: "movieList",
    bindingClass: "MovieResultsList",
  },
  {
    searchParam: "i",
    eventName: "movieDetails",
    bindingClass: "MovieDetailsShow",
  },
  {
    searchParam: "i",
    eventName: "wishList",
    bindingClass: "MovieDetailsShow",
  },
];

//Base Class for MovieResults
class MovieResults {
  title;
  year;
  imdbID;
  type;
  poster;

  constructor(title, year, imdbID, type, poster) {
    this.title = title;
    this.year = year;
    this.imdbID = imdbID;
    this.type = type;
    this.poster = poster;
  }

  get title() {
    return this.title;
  }
  set title(title) {
    this.title = this.title;
  }
  get year() {
    return this.year;
  }
  set year(year) {
    this.year = year;
  }
  get imdbID() {
    return this.imdbID;
  }
  set imdbID(imdbID) {
    this.imdbID = imdbID;
  }
  get type() {
    return this.type;
  }
  set type(type) {
    this.type = type;
  }
  get poster() {
    return this.poster;
  }
  set poster(poster) {
    this.poster = poster;
  }
}

//Base Class for MoveDetail
class MoviDetails extends MovieResults {
  direcotors;
  actors;
  imdbRating;
  plot;
  constructor(
    title,
    year,
    direcotors,
    actors,
    plot,
    poster,
    imdbId,
    imdbRating,
    type
  ) {
    super(title, year, imdbId, type, poster);
    this.direcotors = direcotors;
    this.actors = actors;
    this.plot = plot;
    this.imdbRating = imdbRating;
  }

  get direcotors() {
    return this.direcotors;
  }
  set direcotors(value) {
    this.direcotors = value;
  }

  get actors() {
    return this.actors;
  }
  set actors(value) {
    this.actors = value;
  }

  get plot() {
    return this.plot;
  }
  set plot(value) {
    this.plot = value;
  }

  get imdbRating() {
    return this.imdbRating;
  }
  set imdbRating(value) {
    this.imdbRating = value;
  }
}

//Collection of Movies
class MovieResultsList {
  movieResultsList;
  constructor(movieResult) {
    this.movieResultsList = movieResult;
  }
}

function castJsonWishListToMovieDetails(json) {
  let tempMovieArr = [];
  json.forEach((movieItem) => {
    const movieObj = new MovieResults(
      movieItem.Title,
      movieItem.Year,
      movieItem.imdbID,
      movieItem.Type,
      movieItem.Poster
    );
    tempMovieArr.push(movieObj);
  });
  let movieResultListObj = new MovieResultsList(tempMovieArr);
  return movieResultListObj;
}

//Mapping JSON to Base Classes, MovieResults and MovieResutsList
function castJsonToMovieResult(json) {
  let tempMovieArr = [];

  json.Search.forEach((movieItem) => {
    const movieObj = new MovieResults(
      movieItem.Title,
      movieItem.Year,
      movieItem.imdbID,
      movieItem.Type,
      movieItem.Poster
    );
    tempMovieArr.push(movieObj);
  });
  let movieResultListObj = new MovieResultsList(tempMovieArr);
  return movieResultListObj;
}

function castJsonToMovieDetails(json) {
  const movieDetObj = new MoviDetails(
    json.Title,
    json.Year,
    json.Director,
    json.Actors,
    json.Plot,
    json.Poster,
    json.imdbID,
    json.imdbRating,
    json.Type
  );

  return movieDetObj;
}

//For managing different URLs
function URLBuilder(searParam, searchValue) {
  let url = "";
  url = BASE_URL + "?" + searParam + "=" + searchValue + "&apikey=" + API_KEY;
  return url;
}

//Handles Server Communication and Trigger Events
function serverCommunicator(url, eventAndClassBinding) {
  const loaderIcon = document.getElementById("loaderIco");
  loaderIcon.style.display = "block";
  const resp = fetch(url)
    .then((resp) => {
      return resp.json();
    })
    .then((json) => {
      const eventNotif = new CustomEvent(eventAndClassBinding.eventName, {
        detail: { response: json },
      });
      window.dispatchEvent(eventNotif);
    })
    .catch((err) => {
      console.log("Error fethching request resource..." + err);
      loaderIcon.style.display = "none";
    });
}

function DOMMnuapulator() {
  //Send Request
  const wishlistBtn = document.getElementById("wishlist");
  wishlistBtn.addEventListener("click", () => {
    showWishList();
  });
  const loaderIcon = document.getElementById("loaderIco");
  loaderIcon.style.display = "none";
  const ele_txtSearchInput = document.getElementById("txtSearchInput");
  ele_txtSearchInput.addEventListener("keyup", () => {
    if (ele_txtSearchInput.value.length > 3) {
      const url = URLBuilder(MOVIE_TITLE_PARAM, ele_txtSearchInput.value);
      serverCommunicator(url, EVENT_AND_CLASS_BINDING[0]);
    } else {
      clearSearchResults();
    }
  });

  window.addEventListener(EVENT_AND_CLASS_BINDING[0].eventName, (event) => {
    //console.log(event.detail.response);
    if (!event.detail.response.Error) {
      const movieSearchResults = castJsonToMovieResult(event.detail.response);
      console.log(movieSearchResults);
      refreshMovieListDOM(movieSearchResults);
      const loaderIcon = document.getElementById("loaderIco");
      loaderIcon.style.display = "none";
    }

    //
  });

  window.addEventListener(EVENT_AND_CLASS_BINDING[1].eventName, (event) => {
    console.log(event.detail.response);
    if (!event.detail.response.Error) {
      const movieDetailsObj = castJsonToMovieDetails(event.detail.response);
      console.log(movieDetailsObj);
      fillMovieDetailPosterValues(movieDetailsObj);
      const loaderIcon = document.getElementById("loaderIco");
      loaderIcon.style.display = "none";
    }
  });
}

function clearSearchResults() {
  const movieDetailSec = document.getElementById("resultSection");
  if (movieDetstatus == true) {
    movieDetailSec.style.display = "block";
  } else {
    movieDetailSec.style.display = "none";
  }

  const parent = document.getElementById("searchResultList");
  const ul = document.getElementById("ulList");
  while (ul.hasChildNodes()) {
    ul.removeChild(ul.firstChild);
  }
  parent.style.display = "none";
}

function refreshMovieListDOM(movieSearchResults) {
  const parent = document.getElementById("searchResultList");
  const movieDetailSec = document.getElementById("resultSection");
  movieDetailSec.style.display = "none";
  const parentItem = document.getElementById("ulList");
  while (parentItem.hasChildNodes()) {
    parentItem.removeChild(parentItem.firstChild);
  }
  movieSearchResults.movieResultsList.forEach((item) => {
    const divP = prepareMovieResultTile(item);
    parentItem.appendChild(divP);
  });
  parentItem.style.overflow = "scroll";
  parent.style = `.search-result-list{
    position: fixed;
    top: 60px;
    left: 184px;
    width: 70%;
    background-color: black;
    color: antiquewhite;
    opacity: 80%;
    display: none;
    overflow:scroll;
}`;
  parent.appendChild(parentItem);
  parent.style.display = "block";
}

DOMMnuapulator();

function prepareMovieResultTile(item) {
  //         <li>
  const li = document.createElement("div");
  //             <div class="new-search-div" id="searchSmallTile">
  const newSearchDiv = document.createElement("div");
  newSearchDiv.classList.add("new-search-div");
  newSearchDiv.id = "searchSmallTile";

  //                 <div class="thumbnail-new">
  const thumbnailNew = document.createElement("div");
  thumbnailNew.classList.add("thumbnail-new");

  //                     <img src="https://m.media-amazon.com/images/M/MV5BZDEyN2NhMjgtMjdhNi00MmNlLWE5YTgtZGE4MzNjMTRlMGEwXkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_SX300.jpg">
  const img = document.createElement("img");
  img.src = item.poster;

  //                 </div>
  thumbnailNew.appendChild(img);

  //                 <div class="movie-name-year">
  const divMovieNameYear = document.createElement("div");
  divMovieNameYear.classList.add("movie-name-year");

  //                     <p id="movie-title-new">Avenges- End Game</h3>
  const movieTitleNew = document.createElement("p");
  movieTitleNew.id = "movie-title-new";
  movieTitleNew.textContent = item.title;

  //                     <p id="movie-year-new">2020</h4>
  const movieYearNew = document.createElement("p");
  movieYearNew.id = "movie-year-new";
  movieYearNew.textContent = item.year;

  //                 </div>
  divMovieNameYear.appendChild(movieTitleNew);
  divMovieNameYear.appendChild(movieYearNew);
  //             </div>
  newSearchDiv.appendChild(thumbnailNew);
  newSearchDiv.appendChild(divMovieNameYear);
  const bookmark = document.createElement("i");
  bookmark.classList.add("fa-solid", "fa-bookmark", "tile");
  bookmark.id = `_${item.imdbID}`;
  bookmark.addEventListener("click", () => {
    const imdbId = bookmark.id.slice(1, bookmark.id.length);
    addToWishList(imdbId);
  });
  newSearchDiv.appendChild(bookmark);
  //         </li>
  li.id = "_" + item.imdbID;
  li.addEventListener("click", () => {
    movieDetstatus = true;
    const movieDetailSec = document.getElementById("resultSection");
    movieDetailSec.style.display = "block";
    clearSearchResults();
    showMovieDetailPoster(item.imdbID);
  });

  li.appendChild(newSearchDiv);
  return li;
  //     </ul>
  // </div>
}

function showMovieDetailPoster(imdbID) {
  movieDetstatus = true;
  const url = URLBuilder(IMDB_ID_PARAM, imdbID);
  serverCommunicator(url, EVENT_AND_CLASS_BINDING[1]);
}

function fillMovieDetailPosterValues(moviDetailObj) {
  const detailBookmark = document.getElementById("detailBookmark");

  const hiddenImdbId = document.getElementById("hiddenImdbId");
  hiddenImdbId.textContent = moviDetailObj.imdbID;

  const thumbnailImg = document.getElementById("thumbnailImg");
  thumbnailImg.src = moviDetailObj.poster;

  const rating = document.getElementById("imdbRatingVal");
  rating.textContent = moviDetailObj.imdbRating;

  const movieTitle = document.getElementById("movie-title");
  movieTitle.textContent = moviDetailObj.title;

  const plot = document.getElementById("plot");
  plot.textContent = moviDetailObj.plot;

  const actors = document.getElementById("actors");
  actors.textContent = moviDetailObj.actors;

  const creators = document.getElementById("creators");
  creators.textContent = moviDetailObj.direcotors;

  detailBookmark.addEventListener("click", () => {
    addToWishList(hiddenImdbId.textContent);
  });
}

function addToWishList(imdbId) {
  const found = wishList.find((item) => {
    return item === imdbId;
  });
  if (found === undefined) {
    wishList.push(imdbId);
  } else {
    alert("Already added to Wishlist");
  }
}

movieDetstatus = true;
const url = URLBuilder(IMDB_ID_PARAM, "tt0145487");
serverCommunicator(url, EVENT_AND_CLASS_BINDING[1]);

let lastWishlistItem = "";

function showWishList() {
  clearSearchResults();
  wishList.forEach((item, idx) => {
    const url = URLBuilder(IMDB_ID_PARAM, item);
    serverCommunicator(url, EVENT_AND_CLASS_BINDING[2]);
  });
}

let wishlistArray = [];
const parentItem = document.getElementById("ulList");
window.addEventListener(EVENT_AND_CLASS_BINDING[2].eventName, (event) => {
  //console.log(event.detail.response);
  const responseJson = event.detail.response;
  if (wishlistArray.length < wishList.length) {
    wishlistArray.push(responseJson);
  }
  if (wishlistArray.length === wishList.length) {
    console.log("Last Item Received: " + wishlistArray);
    let filteredWishlist = wishlistArray.filter((item) => {
      if (!item.Error) {
        return item;
      }
    });
    const castedMovieJsonList =
      castJsonWishListToMovieDetails(filteredWishlist);
    refreshMovieListDOM(castedMovieJsonList);
    const loaderIcon = document.getElementById("loaderIco");
    loaderIcon.style.display = "none";

    //wishlistArray.forEach()
  }
});
