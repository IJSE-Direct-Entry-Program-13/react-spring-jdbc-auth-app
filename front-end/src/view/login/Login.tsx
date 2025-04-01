import './Login.css';

function Login() {
    return (
        <main className='d-flex justify-content-center
         align-items-center vh-100'>
            <div className='border rounded p-3 text-center'>
                <h1>
                    <i className='bi bi-lock-fill'></i>
                    Login
                </h1>
                <p>Enter your login credentials to continue</p>
                <div>
                    <label className='mb-2' htmlFor="txt-username">
                        Username</label>
                    <input className='form-control text-center'
                    placeholder='Enter your username here'
                           id='txt-username' type="text"/>
                </div>
                <div className='mt-2'>
                    <label className='mb-2' htmlFor="txt-password">
                        Password</label>
                    <input className='form-control text-center'
                           placeholder='Enter your password here'
                           id='txt-password' type="text"/>
                </div>
                <button className='btn btn-primary mt-3'>Log in</button>
            </div>
        </main>
    );
}

export default Login;