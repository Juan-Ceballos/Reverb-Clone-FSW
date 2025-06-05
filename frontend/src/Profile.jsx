import React, {useEffect, useState} from 'react'
import {useParams} from 'react-router-dom'

const ProfilePage = () => {
    const {username} = useParams()
    const [user, setUser] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/user/${username}`)
            .then(res => res.json())
            .then(data => setUser(data));
    }, [username]);

    if (!user) return <div>Loading...</div>

    return (
        <div>
            <h1>User Details</h1>
            <p>ID: {user.id}</p>
            <p>Username: {user.username}</p>
        </div>
    )
}

export default ProfilePage;