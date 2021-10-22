import './App.css';
import { useEffect, useState } from 'react';
import axios from "axios"

const [userProfileImage, setUserProfileImage] = ([])

const UserProfiles = () => {

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/v1/user-profile").then(res => {
      console.log(res)
    });
  }

  useEffect(() => {
    fetchUserProfiles();
  },[])

  return <h1>Hello Amigos</h1>
}


function App() {
  return (
    <div className="App">
      <UserProfiles/>
    </div>
  );
}

export default App;
