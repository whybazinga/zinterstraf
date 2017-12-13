let path = require('path');
let webpack = require('webpack');

module.exports = {
    entry: {
        'entry':'./src/entry.js'
    },
    output: {
        path: path.resolve(__dirname, 'dest'),
        filename: "[name].js"
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jQuery",
            jQuery: "jQuery",
            "window.jQuery": 'jQuery',
            Popper: ['popper.js', 'default'],
            "window.Popper": ['popper.js', 'default']
        })
    ],
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['env']
                    }
                }
            },
            {
                test: /\.css$/,
                use: [ 'style-loader', 'css-loader' ]
            }
        ]
    }
};