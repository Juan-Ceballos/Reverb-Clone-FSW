import React, {useEffect, useState} from 'react'
import {useParams} from 'react-router-dom'
import ProfilePic from './Profile-Pic';
import ListingForm from './ListingForm';
import ProfileNavBar from './ProfileNavBar';

const ProfilePage = () => {
    const {username} = useParams()
    const [user, setUser] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem('jwt')
        fetch(`http://localhost:8080/user/${username}`,
            {headers: {'Authorization': `Bearer ${token}`}}
        )
            .then(res => res.json())
            .then(data => setUser(data));
    }, [username]);

    if (!user) return <div>Loading...</div>

    return (
        <div>
            <ProfileNavBar></ProfileNavBar>
            <h1>User Details</h1>
            <p>ID: {user.id}</p>
            <p>Username: {user.username}</p>
            <p>Email: {user.email}</p>
            <ProfilePic></ProfilePic>
            <ListingForm></ListingForm>
        </div>
    )
}

export default ProfilePage;