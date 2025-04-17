import React from 'react';
import styles from './App.module.scss';

const App: React.FC = () => {
    console.log('SCSS styles:', styles); // проверь в консоли
    return <div className={styles.app}>Hello, styled world!</div>;
};

export default App;